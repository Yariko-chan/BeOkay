package com.gmail.f.d.ganeeva.beokay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Diana on 28.10.2017 at 18:20.
 */

public class BaseFragmentActivity extends FragmentActivity {

    protected BaseViewModel viewModel;

    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.release();
    }
}
