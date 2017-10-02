package com.gmail.f.d.ganeeva.beokay.diary.add

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText

import com.github.aakira.expandablelayout.ExpandableLayout
import com.github.aakira.expandablelayout.ExpandableLayoutListener
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.aakira.expandablelayout.Utils
import com.gmail.f.d.ganeeva.beokay.R
import kotlinx.android.synthetic.main.view_expandable_edittext.view.*

/**
 * Created by Diana on 01.10.2017 at 13:05.
 */

class ExpandableEditText : ExpandableRelativeLayout {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.view_expandable_edittext, this)

        // class extends from RelativeLayout,
        // there is no way to access android:text directly (it's in EditText, TextView, etc.)
        // added custom attribute
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.ExpandableEditText, 0, 0)
        val text = a.getString(R.styleable.ExpandableEditText_text)
        setEditText(text)
        expandable.setListener(ExpandCollapseListener())
    }

    override fun setListener(listener: ExpandableLayoutListener) {
        super.setListener(ExpandCollapseListener(listener))
    }

    fun setEditText(text: String?) {
        if (TextUtils.isEmpty(text)) editText.setText("") else editText.setText(text)
    }

    fun getEditText() : String {
        return editText.text.toString()
    }

    inner class ExpandCollapseListener : ExpandableLayoutListener {

        // lib accepts only one listener
        // outer listener invoked inside this
        var additionalListener : ExpandableLayoutListener? = null

        constructor(additionalListener: ExpandableLayoutListener) {
            this.additionalListener = additionalListener
        }

        constructor()

        /**
         * clean edittext if collapsed
         */
        override fun onClosed() {
            setEditText("");
            if (additionalListener != null) additionalListener!!.onClosed()
        }


        override fun onOpened() {
            if (additionalListener != null) additionalListener!!.onOpened()
        }

        override fun onAnimationEnd() {
            if (additionalListener != null) additionalListener!!.onAnimationEnd()
        }

        override fun onAnimationStart() {
            if (additionalListener != null) additionalListener!!.onAnimationStart()
        }

        override fun onPreOpen() {
            if (additionalListener != null) additionalListener!!.onPreOpen()
        }

        override fun onPreClose() {
            if (additionalListener != null) additionalListener!!.onPreClose()}
    }
}
