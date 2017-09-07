package com.gmail.f.d.ganeeva.beokay.general;

import android.app.Activity;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Authorization auth = Authorization.getInstance(this);
        if (auth.isSaveLocallyOnly()) {
            HomeActivity.show(this);
        } else if (auth.isStayLogged() && auth.isAuthorized()) {
            HomeActivity.show(this);
        } else {
            AuthorizationActivity.show(this);
        }
    }
}
