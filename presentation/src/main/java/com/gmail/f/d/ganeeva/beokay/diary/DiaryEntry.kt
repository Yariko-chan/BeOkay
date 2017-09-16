package com.gmail.f.d.ganeeva.beokay.diary

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

import kotlinx.android.synthetic.main.card_diary_entrie.view.*

/**
 * Created by Diana on 12.09.2017 at 10:54.
 */

class DiaryEntry : FrameLayout {
    val TAG = DiaryEntry::class.java.simpleName

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
        LayoutInflater.from(context).inflate(R.layout.card_diary_entrie, this)

        expandable.collapse()
        showMoreButton.setOnClickListener { expandable.toggle() }
    }

    private fun parseAttrs(attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.DiaryEntry,
                0, 0)

        try {
            val date = a.getString(R.styleable.DiaryEntry_date)
            val jsonStringArray = a.getString(R.styleable.DiaryEntry_items)

            // set date
            val DATE_REGEX = "(\\d{1,2}\\/\\d{1,2}\\/\\d{4})" // e.g. 22/3/2017
            val p = Pattern.compile(DATE_REGEX)
            if (date != null && p.matcher(date).matches()) {
                dateTextView.text = date
            } else {
                dateTextView.text = ""
            }

            // set items
            var items = arrayOf<String>()
            try {
                if (jsonStringArray != null && jsonStringArray != "") {
                    items = Gson().fromJson(jsonStringArray, Array<String>::class.java)
                }
            } catch (e: JsonSyntaxException) {
                Log.e(TAG, "Invalid JSON, message: " + e.message + " JSON:" + jsonStringArray)
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

        } finally {
            a.recycle()
        }
    }
}
