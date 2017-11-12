package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

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

    public DraftsAdapter adapter = new DraftsAdapter();
    public ObservableBoolean haveData = new ObservableBoolean(false);

    @Inject GetDiaryDraftsUseCase useCase;

    private Fragment fragment;
    private OnDraftsSelectedListener listener;

    public void setListener(OnDraftsSelectedListener listener) {
        this.listener = listener;
    }

    public DiaryDraftsViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
        useCase.dispose();
        fragment = null;
        listener = null;
    }

    @Override
    public void resume() {
        useCase.execute(null, new DisposableObserver<List<? extends String>>() {
            @Override
            public void onNext(@NonNull List<? extends String> strings) {
                haveData.set(true);
                adapter.setDrafts((List<String>) strings);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                // show error
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    public void pause() {
        useCase.dispose();
    }

    public void onOkClick() {
        if (listener != null)
            listener.onDraftsSelected(adapter.getSelectedStrings());
        exit();
    }

    public void onCancelClick() {
        exit();
    }

    public void exit() {
        fragment.getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
