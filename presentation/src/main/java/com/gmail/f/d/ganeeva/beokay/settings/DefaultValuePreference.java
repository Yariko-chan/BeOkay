package com.gmail.f.d.ganeeva.beokay.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;

/**
 * Displays String value instead of summary
 */

public class DefaultValuePreference extends Preference {
    private String defaultValue = "";

    public DefaultValuePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public DefaultValuePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DefaultValuePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultValuePreference(Context context) {
        super(context);

    }

    @Override
    public void onAttached() {
        super.onAttached();
        setSummary(getPersistedString(defaultValue));
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        defaultValue = a.getString(0);
        return defaultValue;
    }
}
