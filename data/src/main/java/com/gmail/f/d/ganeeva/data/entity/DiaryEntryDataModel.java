package com.gmail.f.d.ganeeva.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 20.09.2017 at 22:32.
 */

public class DiaryEntryDataModel implements DataModel {

    @SerializedName("items") private String itemsJson;
    @SerializedName("entryDate") private long dateTimestamp;

    public String getItemsJson() {
        return itemsJson;
    }

    public void setItemsJson(String itemsJson) {
        this.itemsJson = itemsJson;
    }

    public long getDateTimestamp() {
        return dateTimestamp;
    }

    public void setDateTimestamp(long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }
}
