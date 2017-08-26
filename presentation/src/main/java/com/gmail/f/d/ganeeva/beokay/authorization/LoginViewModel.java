package com.gmail.f.d.ganeeva.beokay.authorization;

import android.databinding.ObservableField;

import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.domain.entity.AuthDomainModel;
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.LoginUseCase;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 25.08.2017 at 19:03.
 */

public class LoginViewModel implements BaseViewModel{
    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> error = new ObservableField<>("");

    private LoginUseCase useCase = new LoginUseCase();

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void login() {
        if (login.equals("")) {
            error.set("Login not entered");
            return;
        }
        if (password.equals("")) {
            error.set("Password not entered");
            return;
        }

        AuthDomainModel auth = new AuthDomainModel();
        auth.setLogin(login.get());
        auth.setPassword(password.get());
        useCase.execute(auth, new DisposableObserver<UserDomainModel>() {
            @Override
            public void onNext(@NonNull UserDomainModel userDomainModel) {
                // TODO: open application
                error.set("Authorized");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                error.set(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
