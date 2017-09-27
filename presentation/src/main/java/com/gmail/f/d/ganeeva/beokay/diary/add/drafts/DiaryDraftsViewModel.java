package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.interactions.diary.GetDiaryDraftsUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 18.09.2017 at 8:51.
 */

public class DiaryDraftsViewModel implements BaseViewModel {
    private static final String TAG = DiaryDraftsViewModel.class.getSimpleName();

    public DiaryDraftsListAdapter<DraftViewHolder> adapter = new DiaryDraftsListAdapter<>();
    public ObservableBoolean haveData = new ObservableBoolean(false);

    @Inject
    GetDiaryDraftsUseCase useCase;

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
    }

    @Override
    public void resume() {
        useCase.execute(null, new DisposableObserver<List<? extends String>>() {
            @Override
            public void onNext(@NonNull List<? extends String> strings) {
                haveData.set(true);
                adapter.setItems((List<String>) strings);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pause() {
        useCase.dispose();
    }
}
