package com.gmail.f.d.ganeeva.beokay.general;

import android.app.Application;

import com.gmail.f.d.ganeeva.beokay.general.di.AppComponent;
import com.gmail.f.d.ganeeva.beokay.general.di.DaggerAppComponent;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.RestModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.UseCaseModule;
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
            .restModule(new RestModule())
            .useCaseModule(new UseCaseModule())
            .build();
    }
}
