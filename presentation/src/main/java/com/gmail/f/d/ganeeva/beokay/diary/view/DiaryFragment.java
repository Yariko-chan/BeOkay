package com.gmail.f.d.ganeeva.beokay.diary.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseFragment;
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentDiaryBinding;

public class DiaryFragment extends BaseFragment {

    private FragmentDiaryBinding binding;
    private DiaryViewModel diaryViewModel = new DiaryViewModel();

    public DiaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.viewModel = diaryViewModel;
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_diary, container, false);
        binding.setViewModel(diaryViewModel);

        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (binding != null && diaryViewModel != null) {
            binding.diaryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            binding.diaryList.setAdapter(diaryViewModel.adapter);
        }
    }
}
