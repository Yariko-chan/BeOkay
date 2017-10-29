package com.gmail.f.d.ganeeva.domain.entity;

/**
 * Created by Diana on 21.09.2017 at 14:22.
 */

public class DiaryEntryDomainModel {

    private long entryDateTimestamp;
    private String itemsJsonList;
    private String entryDateString;

    public long getEntryDateTimestamp() {
        return entryDateTimestamp;
    }

    public void setEntryDateTimestamp(long entryDateTimestamp) {
        this.entryDateTimestamp = entryDateTimestamp;
    }

    public String getItems() {
        return itemsJsonList;
    }

    public void setItems(String items) {
        this.itemsJsonList = items;
    }

    public String getEntryDateString() {
        return entryDateString;
    }

    public void setEntryDateString(String entryDateString) {
        this.entryDateString = entryDateString;
    }
}
