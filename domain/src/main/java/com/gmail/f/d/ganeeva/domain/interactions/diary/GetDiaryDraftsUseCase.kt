package com.gmail.f.d.ganeeva.domain.interactions.diary

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase

import io.reactivex.Observable

/**
 * Created by Diana on 18.09.2017 at 8:48.
 */

class GetDiaryDraftsUseCase(internal var restService : RestService) : UseCase<Void, List<String>>() {
    override fun buildUseCase(aVoid: Void?): Observable<List<String>> {
//        val drafts : Observable<Array<DiaryDraft>>
//        val pageSize = 50
//        val offset = 0
//        while(true) {
//            restService.getDiaryDrafts(pageSize, offset)
//                    .observeOn(AndroidSchedulers.mainThread()) // в каком потоке ждём результат here answer (on interface) handled, implemented in main thread
//                    .subscribeOn(Schedulers.newThread()) // в каком потоке выполняется сам код here code(from buildUseCase) implemented, other thread
//                    .subscribeWith{
//                        drafts ->Observable.merge<Array<DiaryDraft>> {  };
//                        e: Throwable -> Log.d("", "");
//                    }
//        }

        // TODO: deal with pagination
        return restService.getDiaryDrafts(50, 0)
                .flatMap { diaryDrafts -> Observable.fromArray(*diaryDrafts) } // emit one-by-one
                .map { draft -> draft.text}                                    // get text from object
                .toList()                                                      // collect to list again (but Single, not Observable)
                .toObservable()                                                // make Observable from Single

        ;
    }
}
