package com.gmail.f.d.ganeeva.domain.interactions.diary

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase

import io.reactivex.Observable

/**
 * Created by Diana on 18.09.2017 at 8:48.
 */

class GetDiaryDraftsUseCase(internal var restService : RestService) : UseCase<Void, List<String>>() {
    override fun buildUseCase(aVoid: Void?): Observable<List<String>> {

        // TODO: deal with pagination
        return restService.getDiaryDrafts(50, 0)
                .flatMap { diaryDrafts -> Observable.fromArray(*diaryDrafts) } // emit one-by-one
                .map { draft -> draft.text}                                    // get text from object
                .toList()                                                      // collect to list again (but Single, not Observable)
                .toObservable()                                                // make Observable from Single

        ;
    }
}
