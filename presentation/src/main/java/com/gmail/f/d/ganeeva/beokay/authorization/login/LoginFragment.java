package com.gmail.f.d.ganeeva.beokay.authorization.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseFragment;
import com.gmail.f.d.ganeeva.beokay.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment {
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        loginViewModel = new LoginViewModel(getActivity());
        this.viewModel = loginViewModel; // viewModel.init() in BaseFragment
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false);
        binding.setAuth(loginViewModel);

        return binding.getRoot();
    }

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
