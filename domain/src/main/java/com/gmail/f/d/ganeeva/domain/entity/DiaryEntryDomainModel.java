package com.gmail.f.d.ganeeva.domain.entity;

import java.util.Date;

/**
 * Created by Diana on 21.09.2017 at 14:22.
 */

public class DiaryEntryDomainModel {

    private String itemsJsonList;
    private String entryDate;

    public String getItems() {
        return itemsJsonList;
    }

    public void setItems(String items) {
        this.itemsJsonList = items;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
