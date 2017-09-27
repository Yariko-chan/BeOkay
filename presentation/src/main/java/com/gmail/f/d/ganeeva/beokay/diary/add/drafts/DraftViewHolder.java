package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.base.BaseItemViewHolder;
import com.gmail.f.d.ganeeva.beokay.databinding.ItemDraftBinding;

/**
 * Created by Diana on 19.09.2017 at 16:16.
 */

public class DraftViewHolder extends BaseItemViewHolder <String, DraftItemViewModel, ItemDraftBinding> {
    public DraftViewHolder(ItemDraftBinding dataBinding, DraftItemViewModel viewModel) {
        super(dataBinding, viewModel);
        dataBinding.setViewModel(viewModel);
    }

    public static DraftViewHolder create(LayoutInflater inflater, ViewGroup parent,
                                            DraftItemViewModel viewModel) {
        ItemDraftBinding binding = ItemDraftBinding.inflate(inflater, parent, false);
        return new DraftViewHolder(binding, viewModel);
    }
}
