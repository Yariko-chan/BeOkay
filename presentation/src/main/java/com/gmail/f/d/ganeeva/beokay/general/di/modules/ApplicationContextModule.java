package com.gmail.f.d.ganeeva.beokay.general.di.modules;

import android.content.Context;

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
public class ApplicationContextModule {
    private Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideApplicationcontext() {
        return context;
    }
}
