package com.gmail.f.d.ganeeva.beokay.authorization.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class LoginViewModel implements BaseViewModel{
    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableBoolean stayLogged = new ObservableBoolean();
    public ObservableField<String> error = new ObservableField<>("");

    public ObservableBoolean isProgress = new ObservableBoolean(false);

    private LoginUseCase useCase = new LoginUseCase();

    private FragmentActivity context;

    LoginViewModel(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        // set saved value for "stay logged"
        Authorization auth = Authorization.getInstance(context);
        stayLogged.set(auth.isStayLogged());

        // set saved login
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String email = pref.getString(context.getResources().getString(R.string.settings_saved_email_key), "");
        login.set(email);
    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        // save auth settings
        Authorization auth = Authorization.getInstance(context);
        auth.setIsStayLogged(context, stayLogged.get());

        // save login to settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit()
            .putString(context.getResources().getString(R.string.settings_saved_email_key), login.get())
            .apply();

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

                // save user token
                Authorization auth = Authorization.getInstance(context);
                auth.setUserToken(context, userDomainModel.getUserToken());

                // save auth settings
                auth.setIsAuthorized(context, true);

                // show app
                HomeActivity.show(context); // put id here?
                context.finish(); // remove from backstack to avoid returning to login activity
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
