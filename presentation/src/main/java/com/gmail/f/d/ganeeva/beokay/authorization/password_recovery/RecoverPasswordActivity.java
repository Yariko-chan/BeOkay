package com.gmail.f.d.ganeeva.beokay.authorization.password_recovery;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseActivity;
import com.gmail.f.d.ganeeva.beokay.databinding.ActivityRecoverPasswordBinding;

public class RecoverPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecoverPasswordViewModel viewModel = new RecoverPasswordViewModel(this);
        this.viewModel = viewModel;

        ActivityRecoverPasswordBinding binding = DataBindingUtil
            .setContentView(RecoverPasswordActivity.this, R.layout.activity_recover_password);
        binding.setViewModel(viewModel);
    }
}
