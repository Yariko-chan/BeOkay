package com.gmail.f.d.ganeeva.beokay.diary.add;

import android.databinding.InverseMethod;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

    @Override
    public void init() {
    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void saveEntries() {
        // create array from entries
        String[] entriesArray = {entry1.get(), entry2.get(), entry3.get(), entry4.get(),
            entry5.get(), entry6.get(), entry7.get(), entry8.get(), entry9.get(), entry10.get()};
        ArrayList<String> entries = new ArrayList<>(Arrays.asList(entriesArray));

        // delete void
        Iterator<String> it = entries.iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty(it.next())) {
                it.remove();
            }
        }

        // convert to json array

        // send to server object
    }
}
