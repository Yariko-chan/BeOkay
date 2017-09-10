package com.gmail.f.d.ganeeva.beokay.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity;
import com.gmail.f.d.ganeeva.beokay.general.Authorization;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings_preferences);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }

    @Override
    protected void onBindPreferences() {
        super.onBindPreferences();

        final Context context = getActivity();
        String key = context.getResources().getString(R.string.settings_saved_email_key);
        Preference logoutPref = findPreference(key);
        logoutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                logout(context);
                return true;
            }
        });
    }

    private void logout(Context context) {
        // set auth settings
        Authorization auth = Authorization.getInstance(context);
        auth.setIsAuthorized(context, false);
        auth.cleanToken(context);

        // open auth activity
        AuthorizationActivity.show(context);
    }
}
