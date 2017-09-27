package com.gmail.f.d.ganeeva.domain.interactions.diary

import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel
import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase
import com.gmail.f.d.ganeeva.domain.utils.convertDiaryEntryDataToDomain

import io.reactivex.Observable

/**
 * Created by Diana on 21.09.2017 at 14:21.
 */

class GetDiaryEntriesUseCase(private val restService: RestService) : UseCase<String, List<DiaryEntryDomainModel>>() {

    override fun buildUseCase(email: String): Observable<List<DiaryEntryDomainModel>> {
        return restService.getDiaryEntries(email)
                .flatMap{ diaryEntries -> Observable.fromArray(*diaryEntries) }
                .map{ diaryEntry -> convertDiaryEntryDataToDomain(diaryEntry)}
                .toList()
                .toObservable()
    }
}
