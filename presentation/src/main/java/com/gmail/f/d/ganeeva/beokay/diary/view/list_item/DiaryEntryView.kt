package com.gmail.f.d.ganeeva.beokay.diary.view.list_item

import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

import com.gmail.f.d.ganeeva.beokay.R
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

import java.util.regex.Pattern

import kotlinx.android.synthetic.main.view_diary_entry.view.*


/**
 * Created by Diana on 12.09.2017 at 10:54.
 *
 * Custom view, contains date (format 22/3/2017) and [3-10] strings
 * 3 strings and date are always visible,
 * others may be hidden or shown by showMore button (ellipsis)
 *
 * R.layout.view_diary_entry
 */

class DiaryEntryView : FrameLayout {
    val TAG = DiaryEntryView::class.java.simpleName

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
        parseAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
        parseAttrs(attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
        parseAttrs(attrs)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_diary_entry, this)

        expandable.collapse() // bug in library, collapsing doesnt work in xml
        showMoreButton.setOnClickListener { expandable.toggle() }
    }

    private fun parseAttrs(attrs: AttributeSet?) {
        val attrs = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.DiaryEntryView,
                0, 0)

        try {
            val date = attrs.getString(R.styleable.DiaryEntryView_date)
            val jsonArrayString = attrs.getString(R.styleable.DiaryEntryView_items)

            // set date
            if (date != null) setDate(date)

            // set items
            if (jsonArrayString != null) setItems(jsonArrayString)

        } finally {
            attrs.recycle()
        }
    }

    fun setItems(jsonArrayString: String) {
        var items = arrayOf<String>()
        try {
            if (jsonArrayString != null && jsonArrayString != "") {
                items = Gson().fromJson(jsonArrayString, Array<String>::class.java)
            }
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "Invalid JSON, message: " + e.message + " JSON:" + jsonArrayString)
        }
        val textViews = arrayOf(goodness1, goodness2, goodness3, goodness4, goodness5,
                goodness6, goodness7, goodness8, goodness9, goodness10)

        // set values
        for ((index, value) in items.withIndex()) {
            textViews[index].text = value
        }

        // make void textViews gone
        for ((index, value) in textViews.withIndex()) {
            if (index >= items.size) {
                value.visibility = View.GONE
            }
        }
    }

    fun setDate(date: String) {
        val DATE_REGEX = "(\\d{1,2}\\/\\d{1,2}\\/\\d{4})" // e.g. 22/3/2017
        val p = Pattern.compile(DATE_REGEX)
        if (date != null && p.matcher(date).matches()) {
            dateTextView.text = date
        } else {
            dateTextView.text = ""
        }
    }


}
