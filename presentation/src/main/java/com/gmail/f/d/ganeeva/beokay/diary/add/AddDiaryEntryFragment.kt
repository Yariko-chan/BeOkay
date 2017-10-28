package com.gmail.f.d.ganeeva.beokay.diary.add

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import com.github.aakira.expandablelayout.ExpandableLayoutListener
import com.gmail.f.d.ganeeva.beokay.R
import com.gmail.f.d.ganeeva.beokay.base.BaseDialogFragment
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentDiaryAddBinding
import com.gmail.f.d.ganeeva.beokay.diary.add.drafts.DiaryDraftsFragment
import kotlinx.android.synthetic.main.fragment_diary_add.*

/**
 * Fragment containing 10 editTexts
 * 3 first EditTexts are compulsory
 * 7 other are optional, hidden at start
 * @addItemButton is for showing first hidden item in range 4..10
 * if all shown, button invisible and !enabled
 * @removeItemButton is for hiding last visible item in range 4..10
 * if all hidden, button invisible and !enabled
 * @items in range 4..10 are EditTexts inside ExpandableRelativeLayout(ERL)
 * hide item = collapse ERL
 * show item = expand ERL
 *
 * Created by Diana on 16.09.2017 at 18:24.
 */

class AddDiaryEntryFragment : BaseDialogFragment() {

    // expandable strings
    private var items = arrayOf<ExpandableEditText>();

    /**
     * inflating view, binding viewModel
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val addDiaryEntryViewModel = AddDiaryEntryViewModel()
        this.viewModel = addDiaryEntryViewModel
        val binding : FragmentDiaryAddBinding = DataBindingUtil.inflate<FragmentDiaryAddBinding>(inflater,
                R.layout.fragment_diary_add, container, false)
        binding.viewModel = addDiaryEntryViewModel

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    /**
     * init ui elements
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = arrayOf(
                item4_expandable, item5_expandable, item6_expandable, item7_expandable,
                item8_expandable, item9_expandable, item10_expandable)

        addItemButton.setOnClickListener{ addEditText() }
        removeItemButton.setOnClickListener{ removeLastEditText() }

        item4_expandable.setAdditionalListener(removeButtonAvailability) // button available if 4th item expanded
        item10_expandable.setAdditionalListener(addButtonAvailability) // button available if 10h item collapsed

        addItemFromDrafts.setOnClickListener{ openDrafts() }
    }

    private fun openDrafts() {
        val fragment = DiaryDraftsFragment()
        fragment.show(fragmentManager, "DRAFTS")
    }

    /**
     * build dialog with animation + fullscreen
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity.layoutInflater.inflate(R.layout.fragment_diary_add, LinearLayout(activity), false)

        // Build dialog
        val builder = Dialog(activity)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        builder.window.attributes.windowAnimations = R.style.AddDiaryEntryDialogAnimation
        builder.setContentView(view)
        return builder
    }

    override fun onStart() {
        super.onStart()

        // show fullscreen
        if (dialog != null) {
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    /**
     * showing first hidden item in range 4..10
     */
    private fun addEditText() {
        for (item in items) {
            if (!item.isExpanded) {
                item.expand()
                return
            }
        }
    }

    /**
     * hiding last visible item in range 4..10
     */
    private fun removeLastEditText() {
        val items = arrayOf(item4_expandable, item5_expandable, item6_expandable, item7_expandable,
                item8_expandable, item9_expandable, item10_expandable)
        for (item in items.reversedArray()) {
            if (item.isExpanded) {
                item.collapse()
                return
            }
        }
    }

    /**
     * listener for binding availability of removeItemButton
     * to visibility of concrete item
     */
    private val removeButtonAvailability = object : ExpandableLayoutListener {
        override fun onAnimationStart() {}
        override fun onAnimationEnd() {}
        override fun onPreOpen() {}
        override fun onPreClose() {}

        // show when opened
        override fun onOpened() {
            removeItemButton.visibility = View.VISIBLE
            removeItemButton.isEnabled = true
        }

        // hide when closed
        override fun onClosed() {
            removeItemButton.visibility = View.INVISIBLE
            removeItemButton.isEnabled = false
        }
    }

    /**
     * listener for binding availability of addItemButton
     * to visibility of concrete item
     */
    private val addButtonAvailability = object : ExpandableLayoutListener {
        override fun onAnimationStart() {}
        override fun onAnimationEnd() {}
        override fun onPreOpen() {}
        override fun onPreClose() {}

        // hide when opened
        override fun onOpened() {
            addItemButton.visibility = View.INVISIBLE
            addItemButton.isEnabled = false
        }

        // show when closed
        override fun onClosed() {
            addItemButton.visibility = View.VISIBLE
            addItemButton.isEnabled = true
        }
    }
}
