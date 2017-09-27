package com.gmail.f.d.ganeeva.beokay.authorization.password_recovery;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.interactions.auth.RecoverPasswordUseCase;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

/**
 * Created by Diana on 29.08.2017 at 10:04.
 */

public class RecoverPasswordViewModel implements BaseViewModel {
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableBoolean isProgress = new ObservableBoolean(false);
    public ObservableField<String> error = new ObservableField<>("");

    private RecoverPasswordActivity context;

    @Inject RecoverPasswordUseCase useCase;

    public RecoverPasswordViewModel(RecoverPasswordActivity context) {
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

    public void sendEmail() {
        if (email.get().equals("")) {
            error.set(context.getString(R.string.msg_login_void));
        }

        // send a request, wait if error, else close activity
        useCase.execute(email.get(), new DisposableObserver<Response<Void>>() {
                @Override
                public void onNext(@NonNull Response<Void> voidResponse) {
                    // close activity
                    context.finish();
                    // display toast with message We'll sent you an email for recovering password
                    Toast.makeText(context.getApplicationContext(),
                        "Please, check email and follow link in the letter", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    // TODO: make catching errors
                    // TODO: make this activity animate like it arises from the center of screen
                    error.set(e.getMessage());

                }

                @Override
                public void onComplete() {

                }
            });
    }

}
