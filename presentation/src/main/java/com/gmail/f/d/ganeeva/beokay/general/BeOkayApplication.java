package com.gmail.f.d.ganeeva.beokay.general;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


public class BeOkayApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
