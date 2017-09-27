package com.gmail.f.d.ganeeva.beokay.authorization.register;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.auth.RegisterUseCase;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

import static com.gmail.f.d.ganeeva.beokay.general.utils.ErrorsHandlingKt.getErrorMessage;

/**
 * Created by Diana on 27.08.2017 at 19:18.
 */

public class RegisterViewModel implements BaseViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> error = new ObservableField<>("");

    public ObservableBoolean isProgress = new ObservableBoolean(false);
    private final AuthorizationActivity context;

    @Inject RegisterUseCase useCase;
    @Inject Context applicationContext;

    public RegisterViewModel(AuthorizationActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
        useCase.dispose();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
    }

    public void register() {
        if (login.get().equals("")) {
            error.set("Login not entered");
            return;
        }
        if (password.get().equals("")) {
            error.set("Password not entered");
            return;
        }

        isProgress.set(true);
        UserDomainModel user = new UserDomainModel();
        user.setEmail(login.get());
        user.setPassword(password.get());
        user.setName(name.get());
        useCase.execute(user, new DisposableObserver<UserDomainModel>() {
            @Override
            public void onNext(@NonNull UserDomainModel userDomainModel) {
                // TODO: some logic opening activity
                error.set(context.getString(R.string.msg_confirm_email) );
                isProgress.set(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isProgress.set(false);
                error.set(getErrorMessage(applicationContext, e));
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
