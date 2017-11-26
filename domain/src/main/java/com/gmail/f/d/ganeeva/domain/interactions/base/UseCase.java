package com.gmail.f.d.ganeeva.domain.interactions.base;

import com.gmail.f.d.ganeeva.data.net.RestService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Diana on 11.08.2017.
 */

public abstract class UseCase<InParam, OutParam> {

    private Disposable disposable;

    // how to retrieve data concerning threads, but concrete process of retrieving data is in childs
    public void execute(InParam param, DisposableObserver<OutParam> disposableObserver) {

        disposable = buildUseCase(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(disposableObserver); //  subscribes to result of buildUseCase()
}

    public abstract Observable<OutParam> buildUseCase(InParam param); // gets data from Data layer

    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
