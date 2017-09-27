package com.gmail.f.d.ganeeva.beokay.diary.view.list_item

import android.databinding.BindingAdapter

/**
 * Created by Diana on 27.09.2017 at 11:28.
 *
 * Binding adapters for custom DiaryEntryView
 */

@BindingAdapter("app:date")
fun setDateAttr(view: DiaryEntryView, date: String) {
    view.setDate(date)
}

@BindingAdapter("app:items")
fun setItemsAttr(view: DiaryEntryView, itemsList: String) {
    view.setItems(itemsList)
}