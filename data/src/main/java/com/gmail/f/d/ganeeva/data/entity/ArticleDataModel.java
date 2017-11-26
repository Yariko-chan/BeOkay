package com.gmail.f.d.ganeeva.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 19.11.2017 at 14:13.
 */

public class ArticleDataModel implements DataModel {
    @SerializedName("title")
    private String title;

    @SerializedName("text")
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
