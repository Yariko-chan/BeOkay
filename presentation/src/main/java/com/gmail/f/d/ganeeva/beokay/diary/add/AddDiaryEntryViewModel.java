package com.gmail.f.d.ganeeva.beokay.diary.add;

import android.content.Context;
import android.databinding.InverseMethod;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

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

    @Inject SaveDiaryEntryUC useCase;
    @Inject Context applicationContext;

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
        useCase.dispose();
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

        // convert to json array
        String jsonArray = new Gson().toJson(entries);

        // send to server object
        useCase.execute(jsonArray, Authorization.getInstance(applicationContext).getUserToken(),
            new DisposableObserver<DiaryEntryDomainModel>() {
            @Override
            public void onNext(@NonNull DiaryEntryDomainModel diaryEntryDomainModel) {
                Log.d("", "");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("", "");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
