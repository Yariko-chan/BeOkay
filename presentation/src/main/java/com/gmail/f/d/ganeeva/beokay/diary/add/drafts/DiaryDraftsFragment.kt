package com.gmail.f.d.ganeeva.beokay.diary.add.drafts

import android.app.ActionBar
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gmail.f.d.ganeeva.beokay.R
import com.gmail.f.d.ganeeva.beokay.base.BaseDialogFragment
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentDiaryDraftsBinding
import android.view.WindowManager



/**
 * Created by Diana on 18.09.2017 at 8:11.
 */

class DiaryDraftsFragment : BaseDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val draftsViewModel = DiaryDraftsViewModel()
        this.viewModel = draftsViewModel
        val binding : FragmentDiaryDraftsBinding = DataBindingUtil.inflate<FragmentDiaryDraftsBinding>(inflater,
                R.layout.fragment_diary_drafts, container, false)
        binding.viewModel = draftsViewModel
        binding.draftsList.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        binding.draftsList.adapter = draftsViewModel.adapter

        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = Dialog(activity)
        builder.window.attributes.windowAnimations = R.style.DiaryDraftsDialogAnimation
        return builder
    }

    override fun onStart() {
        super.onStart()
        getDialog().getWindow().setLayout(
                getResources().getDisplayMetrics().widthPixels*5/6,
                getResources().getDisplayMetrics().heightPixels*3/4
        );
    }
}
