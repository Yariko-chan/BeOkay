package com.gmail.f.d.ganeeva.domain.interactions.diary

import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel
import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel
import com.gmail.f.d.ganeeva.domain.utils.convertDiaryEntryDataToDomain
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Diana on 21.10.2017 at 14:12.
 */

class SaveDiaryEntryUC(private val restService: RestService) {

    private var disposable: Disposable? = null

    fun buildUseCase(diaryEntryJson: String, token: String): Observable<DiaryEntryDomainModel> {

        val diaryEntry = DiaryEntryDataModel()
        diaryEntry.itemsJson = diaryEntryJson
        diaryEntry.dateTimestamp = System.currentTimeMillis()
        return restService.saveDiaryEntry(diaryEntry, token)
                .map{ diaryEntry -> convertDiaryEntryDataToDomain(diaryEntry)}
    }

    fun execute(diaryEntryJson: String, token: String, disposableObserver: DisposableObserver<DiaryEntryDomainModel>) {

        disposable = buildUseCase(diaryEntryJson, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(disposableObserver) //  subscribes to result of buildUseCase()
    }

    fun dispose() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}
