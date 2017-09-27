package com.gmail.f.d.ganeeva.beokay.diary.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.base.BaseAdapter;
import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewHolder;
import com.gmail.f.d.ganeeva.beokay.diary.view.list_item.DiaryItemViewHolder;
import com.gmail.f.d.ganeeva.beokay.diary.view.list_item.DiaryItemViewModel;
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel;

/**
 * Created by Diana on 27.09.2017 at 7:57.
 */

public class DiaryListAdapter<Holder extends DiaryItemViewHolder>
    extends BaseAdapter<DiaryEntryDomainModel, DiaryItemViewModel>
{
    @Override
    public BaseItemViewHolder<DiaryEntryDomainModel, DiaryItemViewModel, ?>
    onCreateViewHolder(ViewGroup parent, int viewType) {
        DiaryItemViewModel viewModel = new DiaryItemViewModel();
        DiaryItemViewHolder holder =
            Holder.create(LayoutInflater.from(parent.getContext()), parent, viewModel);
        return holder;
    }
}
