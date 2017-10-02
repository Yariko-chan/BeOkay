package com.gmail.f.d.ganeeva.beokay

import android.databinding.BindingAdapter
import com.gmail.f.d.ganeeva.beokay.diary.add.ExpandableEditText
import com.gmail.f.d.ganeeva.beokay.diary.view.list_item.DiaryEntryView
import android.databinding.InverseBindingAdapter
import android.text.Editable
import android.text.TextWatcher
import android.databinding.InverseBindingListener
import kotlinx.android.synthetic.main.view_expandable_edittext.view.*


/**
 * Created by Diana on 27.09.2017 at 11:28.
 *
 * Binding adapters for custom views
 */


/**
 * DiaryEntryView
 */
@BindingAdapter("app:date")
fun setDateAttr(view: DiaryEntryView, date: String) {
    view.setDate(date)
}

@BindingAdapter("app:items")
fun setItemsAttr(view: DiaryEntryView, itemsList: String) {
    view.setItems(itemsList)
}

/**
 * ExpandableEditText
 */
@BindingAdapter("app:text")
fun setText(view: ExpandableEditText, text: String) {
    if (view.getEditText().equals(text)) return; // prevent infinite loop
    view.setEditText(text)
}

@InverseBindingAdapter(attribute = "app:text",
        event = "custom:textAttrChanged")
fun getText(view: ExpandableEditText): String {
    return view.getEditText()
}

/**
 * hook up a listener to know when the value in EditText changes.
 */
@BindingAdapter(value = "textAttrChanged")
fun setListener(view: ExpandableEditText, listener: InverseBindingListener?) {
    if (listener != null) {
        view.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                listener.onChange()
            }
        })
    }
}