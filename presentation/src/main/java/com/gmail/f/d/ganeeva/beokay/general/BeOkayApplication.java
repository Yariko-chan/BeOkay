package com.gmail.f.d.ganeeva.beokay.general;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.general.di.AppComponent;
import com.gmail.f.d.ganeeva.beokay.general.di.DaggerAppComponent;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.ApplicationModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.RestModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.UseCaseModule;
import com.squareup.leakcanary.LeakCanary;

public class BeOkayApplication extends Application {
    private static final String TAG = BeOkayApplication.class.getSimpleName();
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
            .applicationModule(new ApplicationModule(this))
            .restModule(new RestModule())
            .useCaseModule(new UseCaseModule())
            .build();

        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            String versionName = getPackageManager()
                .getPackageInfo(getPackageName(), 0).versionName;
            pref.edit()
                // saved keys to resources to be able to use in preferences .xml file
                .putString(getString(R.string.settings_version_key), versionName)
                .apply();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Error saving version name: " + e.getMessage());
        }
    }

    private OnDataChangedListener dataChangeListener = null;

    public void setDataChangeListener(OnDataChangedListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    public OnDataChangedListener getDataChangeListener() {
        return dataChangeListener;
    }

    public void removeDataChangeListener() {
        this.dataChangeListener = null;
    }
}
