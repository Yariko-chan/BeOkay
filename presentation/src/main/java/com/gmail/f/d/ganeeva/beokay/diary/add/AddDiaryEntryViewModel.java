package com.gmail.f.d.ganeeva.beokay.diary.add;

import android.app.Activity;
import android.content.Context;
import android.databinding.InverseMethod;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseDialogFragment;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.Authorization;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.diary.GetDiaryEntriesUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.diary.SaveDiaryEntryUC;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 17.09.2017 at 9:41.
 */

public class AddDiaryEntryViewModel implements BaseViewModel {
    private static final String TAG = AddDiaryEntryViewModel.class.getSimpleName();

    public ObservableField<String> entry1 = new ObservableField<>("");
    public ObservableField<String> entry2 = new ObservableField<>("");
    public ObservableField<String> entry3 = new ObservableField<>("");
    public ObservableField<String> entry4 = new ObservableField<>("");
    public ObservableField<String> entry5 = new ObservableField<>("");
    public ObservableField<String> entry6 = new ObservableField<>("");
    public ObservableField<String> entry7 = new ObservableField<>("");
    public ObservableField<String> entry8 = new ObservableField<>("");
    public ObservableField<String> entry9 = new ObservableField<>("");
    public ObservableField<String> entry10 = new ObservableField<>("");

    public ObservableBoolean min3Filled = new ObservableBoolean(true);

    @Inject SaveDiaryEntryUC useCase;
    @Inject Context applicationContext;
    @Inject BeOkayApplication application;

    private Fragment fragment;

    public AddDiaryEntryViewModel(Fragment fragment) {
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
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void saveEntries() {
        // create array from entries
        String[] entriesArray = {
            entry1.get(), entry2.get(), entry3.get(), entry4.get(), entry5.get(),
            entry6.get(), entry7.get(), entry8.get(), entry9.get(), entry10.get()};
        ArrayList<String> entries = new ArrayList<>(Arrays.asList(entriesArray));

        // delete void
        Iterator<String> it = entries.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(it.next())) {
                it.remove();
            }
        }

        // check min 3 filled
        if (entries.size() < 3) {
            min3Filled.set(false);
            return;
        } else {
            min3Filled.set(true);
        }

        // convert to json array
        String jsonArray = new Gson().toJson(entries);

        // send to server object
        ((AddDiaryEntryFragment) fragment).showProgress();
        useCase.execute(jsonArray, Authorization.getInstance(applicationContext).getUserToken(),
            new DisposableObserver<DiaryEntryDomainModel>() {
            @Override
            public void onNext(@NonNull DiaryEntryDomainModel diaryEntryDomainModel) {
                ((AddDiaryEntryFragment) fragment).dismissProgress();
                application.getDataChangeListener().onDataChanged();
                exit();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "Error saving diary entries: " + e);
                ((AddDiaryEntryFragment) fragment).dismissProgress();
                Toast.makeText(applicationContext, R.string.msg_diary_entry_not_saved, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {}
        });
    }

    public void exit() {
        fragment.getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
