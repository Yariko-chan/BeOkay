package com.gmail.f.d.ganeeva.beokay.general.di;

import com.gmail.f.d.ganeeva.beokay.authorization.login.LoginViewModel;
import com.gmail.f.d.ganeeva.beokay.authorization.password_recovery.RecoverPasswordViewModel;
import com.gmail.f.d.ganeeva.beokay.authorization.register.RegisterViewModel;
import com.gmail.f.d.ganeeva.beokay.general.LauncherActivity;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.RestModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.UseCaseModule;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RestModule.class, UseCaseModule.class})
@Singleton
public interface AppComponent {

    void inject(LoginViewModel viewModel);

    void inject(RegisterViewModel viewModel);

    void inject(RecoverPasswordViewModel viewModel);

    void inject(@NotNull LauncherActivity activity);
}
