package com.gmail.f.d.ganeeva.beokay.authorization.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.base.BaseFragment;
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentRegisterBinding;

public class RegisterFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public RegisterFragment() {
        Log.d("", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AuthorizationActivity activity = (AuthorizationActivity) getActivity();
        RegisterViewModel registerViewModel = new RegisterViewModel(activity);
        this.viewModel = registerViewModel;
        FragmentRegisterBinding binding  = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false);
        binding.setUser(registerViewModel);

        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
