package com.gmail.f.d.ganeeva.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 18.09.2017 at 17:14.
 */

public class DiaryDraftDataModel implements DataModel {
    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
