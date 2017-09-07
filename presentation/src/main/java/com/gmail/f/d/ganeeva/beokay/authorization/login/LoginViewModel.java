package com.gmail.f.d.ganeeva.beokay.authorization.login;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.authorization.password_recovery.RecoverPasswordActivity;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.Authorization;
import com.gmail.f.d.ganeeva.beokay.general.HomeActivity;
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
    public ObservableBoolean stayLogged = new ObservableBoolean();
    public ObservableField<String> error = new ObservableField<>("");

    public ObservableBoolean isProgress = new ObservableBoolean(false);

    private LoginUseCase useCase = new LoginUseCase();

    private FragmentActivity context;

    public LoginViewModel(FragmentActivity context) {
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
            error.set(context.getString(R.string.msg_login_void));
            return;
        }
        if (password.get().equals("")) {
            error.set(context.getString(R.string.msg_password_void));
            return;
        }


        isProgress.set(true);

        AuthDomainModel auth = new AuthDomainModel(login.get(), password.get());
        useCase.execute(auth, new DisposableObserver<UserDomainModel>() {
            @Override
            public void onNext(@NonNull UserDomainModel userDomainModel) {
                // TODO: save user token here
                Authorization auth = Authorization.getInstance(context);
                auth.setUserToken(context, userDomainModel.getUserToken());
                auth.setIsAuthorized(context, true);
                if (stayLogged.get()) auth.setIsStayLogged(context, true);
                HomeActivity.show(context); // put id here?
                context.finish(); // remove from backstack to avoid returning to login activity
//                error.set("Authorized");
//                isProgress.set(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isProgress.set(false);

                if (e instanceof HttpException) {
                    switch (((HttpException) e).code()) {
                        case 401 : error.set(context.getString(R.string.msg_incorrect_auth_data)); break;
                        case 400 : error.set(context.getString(R.string.msg_choose_other_account)); break;
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

    public void recover() {
        Intent intent = new Intent(context, RecoverPasswordActivity.class);
        context.startActivity(intent);
    }
}
