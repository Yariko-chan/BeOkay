package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.base.BaseAdapter;
import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewHolder;

/**
 * Created by Diana on 19.09.2017 at 16:09.
 */

public class DiaryDraftsListAdapter<Holder extends DraftViewHolder>
    extends BaseAdapter<String, DraftItemViewModel> {
    @Override
    public BaseItemViewHolder<String, DraftItemViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        DraftItemViewModel viewModel = new DraftItemViewModel();
        DraftViewHolder holder = Holder.create(LayoutInflater.from(parent.getContext()), parent, viewModel);
        return holder;
    }
}
