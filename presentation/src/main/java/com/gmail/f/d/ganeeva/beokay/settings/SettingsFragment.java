package com.gmail.f.d.ganeeva.beokay.settings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.general.Authorization;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Inject Context applicationContext;

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings_preferences);
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    @Override
    protected void onBindPreferences() {
        super.onBindPreferences();

        final Context context = getActivity();
        String key = applicationContext.getString(R.string.settings_saved_email_key);
        Preference logoutPref = findPreference(key);
        logoutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                logout(context);
                return true;
            }
        });
    }

    private static void logout(Context context) {
        // set auth settings
        Authorization auth = Authorization.getInstance(context);
        auth.setIsAuthorized(context, false);
        auth.cleanToken(context);

        // open auth activity
        AuthorizationActivity.show(context);
    }
}
