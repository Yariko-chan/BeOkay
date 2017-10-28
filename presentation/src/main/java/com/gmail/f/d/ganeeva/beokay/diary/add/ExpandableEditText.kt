package com.gmail.f.d.ganeeva.beokay.diary.add

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
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
        super.setListener(listener)
    }

    fun setEditText(text: String?) {
        if (TextUtils.isEmpty(text))
            editText.setText("")
        else editText.setText(text)
    }

    fun getEditText() : String {
        return editText.text.toString()
    }

    // listener methods not invoked somehow



    /**
     * listener for binding availability of removeItemButton
     * to visibility of concrete item
     */
    private val listener = object : ExpandableLayoutListener {
        override fun onAnimationStart() {}
        override fun onAnimationEnd() {}
        override fun onPreOpen() {}
        override fun onPreClose() {}

        // show when opened
        override fun onOpened() {
            outerListener?.onOpened() ?: return
        }

        // hide when closed
        override fun onClosed() {
            setEditText("");
            outerListener?.onClosed() ?: return
        }
    }

    var outerListener : ExpandableLayoutListener? = null

    fun setAdditionalListener(outerListener : ExpandableLayoutListener) {
        this.outerListener = outerListener
    }
}
