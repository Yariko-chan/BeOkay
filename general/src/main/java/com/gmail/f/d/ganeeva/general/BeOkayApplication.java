package com.gmail.f.d.ganeeva.general;

import android.app.Application;

import com.gmail.f.d.ganeeva.general.di.AppComponent;
import com.gmail.f.d.ganeeva.general.di.AppModule;
import com.gmail.f.d.ganeeva.general.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class BeOkayApplication extends Application {

    public static AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule())
            .build();
    }
}
