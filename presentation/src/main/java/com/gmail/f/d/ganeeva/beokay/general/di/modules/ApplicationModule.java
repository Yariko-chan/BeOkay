package com.gmail.f.d.ganeeva.beokay.general.di.modules;

import android.content.Context;

import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.data.net.RestAPI;
import com.gmail.f.d.ganeeva.data.net.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diana on 27.09.2017 at 12:39.
 *
 * Module provides application context for accessing resources
 */

@Module
public class ApplicationModule {
    private BeOkayApplication application;

    public ApplicationModule(BeOkayApplication context) {
        this.application = context;
    }

    @Singleton
    @Provides
    public BeOkayApplication provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return application;
    }
}
