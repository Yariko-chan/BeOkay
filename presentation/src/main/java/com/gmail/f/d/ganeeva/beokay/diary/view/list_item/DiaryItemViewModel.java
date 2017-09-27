package com.gmail.f.d.ganeeva.beokay.diary.view.list_item;

import android.databinding.ObservableField;

import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewModel;
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel;

import io.reactivex.Observable;

/**
 * Created by Diana on 27.09.2017 at 8:00.
 */

public class DiaryItemViewModel extends BaseItemViewModel<DiaryEntryDomainModel> {
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> items = new ObservableField<>("");

    @Override
    public void setItem(DiaryEntryDomainModel diaryEntry, int position) {
        date.set(diaryEntry.getEntryDate());
        items.set(diaryEntry.getItems());
    }
}
