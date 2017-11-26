package com.gmail.f.d.ganeeva.beokay.articles;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.entity.ArticleDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.GetArticlesUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 19.11.2017 at 15:02.
 */

public class ArticlesViewModel implements BaseViewModel {
    public ObservableBoolean hasData = new ObservableBoolean(false);
    public ObservableField<String> error = new ObservableField<>("");

    public ArticlesAdapter adapter = new ArticlesAdapter();

    @Inject GetArticlesUseCase useCase;
    @Inject Context applicationContext;

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
    }

    @Override
    public void resume() {
        useCase.execute(null,
            new DisposableObserver<List<? extends ArticleDomainModel>>() {
                @Override
                public void onNext(List<? extends ArticleDomainModel> articleDomainModels) {
                    hasData.set(true);
                    adapter.setArticles((List<ArticleDomainModel>) articleDomainModels);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable e) {
                    error.set(applicationContext.getString(R.string.error_loading_articles));
                }

                @Override
                public void onComplete() {}
            });
    }

    @Override
    public void pause() {
        error.set("");
        hasData.set(false);
        useCase.dispose();
    }
}
