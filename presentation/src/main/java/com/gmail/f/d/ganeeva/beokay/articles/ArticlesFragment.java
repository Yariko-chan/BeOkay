package com.gmail.f.d.ganeeva.beokay.articles;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseFragment;
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentArticlesBinding;

public class ArticlesFragment extends BaseFragment {

    public ArticlesFragment() {
    }

    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArticlesViewModel articlesViewModel = new ArticlesViewModel();
        this.viewModel = articlesViewModel;
        FragmentArticlesBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_articles,container, false);
        binding.setViewModel(articlesViewModel);
        binding.trickList.setLayoutManager(new LinearLayoutManager(
            getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.trickList.setAdapter(articlesViewModel.adapter);
        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }
}
