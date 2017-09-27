package com.gmail.f.d.ganeeva.beokay.diary.view.list_item;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewHolder;
import com.gmail.f.d.ganeeva.beokay.databinding.ItemDiaryEntryBinding;
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel;

/**
 * Created by Diana on 27.09.2017 at 8:10.
 */

public class DiaryItemViewHolder extends BaseItemViewHolder<DiaryEntryDomainModel, DiaryItemViewModel, ItemDiaryEntryBinding> {
    public DiaryItemViewHolder(ItemDiaryEntryBinding dataBinding, DiaryItemViewModel viewModel) {
        super(dataBinding, viewModel);
        dataBinding.setDiaryEntry(viewModel);
    }

    public static DiaryItemViewHolder create(LayoutInflater inflater, ViewGroup parent,
                                             DiaryItemViewModel viewModel) {
        ItemDiaryEntryBinding binding = ItemDiaryEntryBinding.inflate(inflater, parent, false);
        return new DiaryItemViewHolder(binding, viewModel);
    }
}
