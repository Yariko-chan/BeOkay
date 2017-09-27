package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.databinding.ObservableField;

import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewModel;

/**
 * Created by Diana on 19.09.2017 at 16:12.
 */

public class DraftItemViewModel extends BaseItemViewModel<String>{
    public ObservableField<String> draft = new ObservableField<>("");
    @Override
    public void setItem(String item, int position) {
        draft.set(item);
    }
}
