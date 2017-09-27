package com.gmail.f.d.ganeeva.beokay.authorization.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.base.BaseFragment;
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { // viewModel.init() in BaseFragment
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AuthorizationActivity activity = (AuthorizationActivity) getActivity();
        LoginViewModel loginViewModel = new LoginViewModel(activity);
        this.viewModel = loginViewModel;
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false);
        binding.setAuth(loginViewModel);

        super.onCreateView(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
