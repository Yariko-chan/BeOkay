package com.gmail.f.d.ganeeva.beokay.authorization.password_recovery;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseActivity;
import com.gmail.f.d.ganeeva.beokay.databinding.ActivityRecoverPasswordBinding;

import javax.inject.Inject;

public class RecoverPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecoverPasswordViewModel childViewModel = new RecoverPasswordViewModel(this);
        this.viewModel = childViewModel;

        ActivityRecoverPasswordBinding binding = DataBindingUtil
            .setContentView(RecoverPasswordActivity.this, R.layout.activity_recover_password);
        binding.setViewModel(childViewModel);

        super.onCreate(savedInstanceState);
    }
}
