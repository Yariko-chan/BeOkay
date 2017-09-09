package com.gmail.f.d.ganeeva.beokay.general;

import android.app.Activity;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.domain.interactions.ValidateLogin;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Authorization auth = Authorization.getInstance(this);
        if (auth.isSaveLocallyOnly()) {
            HomeActivity.show(this);
        } else if (auth.isStayLogged() && auth.isAuthorized()) {
            String token = auth.getUserToken();
            if (null != token && !token.equals("")) {
                new ValidateLogin().execute(token, new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        HomeActivity.show(LauncherActivity.this);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AuthorizationActivity.show(LauncherActivity.this);
                    }

                    @Override
                    public void onComplete() {}
                });
            } else {
                AuthorizationActivity.show(this);
            }

        } else {
            AuthorizationActivity.show(this);
        }
    }
}
