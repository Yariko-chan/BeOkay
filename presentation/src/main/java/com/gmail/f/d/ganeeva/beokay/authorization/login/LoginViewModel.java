package com.gmail.f.d.ganeeva.beokay.authorization.login;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.MainActivity;
import com.gmail.f.d.ganeeva.domain.entity.AuthDomainModel;
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.LoginUseCase;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Diana on 25.08.2017 at 19:03.
 */

public class LoginViewModel implements BaseViewModel{
    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> error = new ObservableField<>("");

    public ObservableBoolean isProgress = new ObservableBoolean(false);

    private LoginUseCase useCase = new LoginUseCase();

    private Context context;

    public LoginViewModel(Context context) {
        this.context = context;
    }

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
        if (login.get().equals("")) {
            error.set("Login not entered");
            return;
        }
        if (password.get().equals("")) {
            error.set("Password not entered");
            return;
        }


        isProgress.set(true);

        AuthDomainModel auth = new AuthDomainModel(login.get(), password.get());
        useCase.execute(auth, new DisposableObserver<UserDomainModel>() {
            @Override
            public void onNext(@NonNull UserDomainModel userDomainModel) {
                Bundle b = new Bundle();
                b.putString("USER_ID", userDomainModel.getId());
                MainActivity.show(context, b); // put id here?
                error.set("Authorized");
                isProgress.set(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isProgress.set(false);

                if (e instanceof HttpException) {
                    switch (((HttpException) e).code()) {
                        case 401 : error.set(context.getString(R.string.msg_incorrect_auth_data)); break;
                        default: error.set(e.getMessage()); break;
                    }
                } else if (e instanceof SocketTimeoutException){
                    error.set(context.getString(R.string.msg_connection_timed_out));
                } else if (e instanceof UnknownHostException) {
                    error.set(context.getString(R.string.msg_network_error));
                } else {
                    error.set(e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                isProgress.set(false);
            }
        });
    }
}
