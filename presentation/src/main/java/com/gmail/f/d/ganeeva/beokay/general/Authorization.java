package com.gmail.f.d.ganeeva.beokay.general;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class to get variables concerning autorization from SharedPrefs
 * getter and setter methods get and update variables in shared preferences
 */

public class Authorization {

    private static final String AUTH_SHARED_PREFS = "AUTH_SHARED_PREFS";
    private static final String IS_AUTHORIZED_KEY = "IS_AUTHORIZED_KEY";
    private static final String IS_STAY_LOGGED_KEY = "IS_STAY_LOGGED_KEY";
    private static final String IS_SAVELOCALLY_ONLY_KEY = "IS_SAVELOCALLY_ONLY_KEY";
    private static final String USER_TOKEN_KEY = "USER_TOKEN_KEY";

    private boolean isAuthorized = false;
    private boolean isStayLogged = false;
    private boolean isSaveLocallyOnly = false;
    private String userToken = "";

    private static Authorization instance;

    public static Authorization getInstance(Context context) {
        if (null == instance) {
            instance = new Authorization(context);
        }
        return instance;
    }

    private Authorization(Context context) {
        update(context);
    }

    /**
     * update variables from Shared preferences
     */
    public void update(Context context) {
        SharedPreferences pref = context.getSharedPreferences(AUTH_SHARED_PREFS, Context.MODE_PRIVATE);
        isAuthorized = pref.getBoolean(IS_AUTHORIZED_KEY, false);
        isStayLogged = pref.getBoolean(IS_STAY_LOGGED_KEY, false);
        isSaveLocallyOnly = pref.getBoolean(IS_SAVELOCALLY_ONLY_KEY, false);
        userToken = pref.getString(USER_TOKEN_KEY, "");
    }

    public String getUserToken() {
        return userToken;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public boolean isStayLogged() {
        return isStayLogged;
    }

    public boolean isSaveLocallyOnly() {
        return isSaveLocallyOnly;
    }

    public void setUserToken(Context context, String userToken) {
        if (null == userToken) return;
        this.userToken = userToken;
        SharedPreferences pref = context.getSharedPreferences(AUTH_SHARED_PREFS, Context.MODE_PRIVATE);
        pref.edit()
            .putString(USER_TOKEN_KEY, userToken)
            .apply();
    }

    public void setIsAuthorized(Context context, boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        SharedPreferences pref = context.getSharedPreferences(AUTH_SHARED_PREFS, Context.MODE_PRIVATE);
        pref.edit()
            .putBoolean(IS_AUTHORIZED_KEY, isAuthorized)
            .apply();
    }

    public void setIsStayLogged(Context context, boolean isStayLogged) {
        this.isStayLogged = isStayLogged;
        SharedPreferences pref = context.getSharedPreferences(AUTH_SHARED_PREFS, Context.MODE_PRIVATE);
        pref.edit()
            .putBoolean(IS_STAY_LOGGED_KEY, isStayLogged)
            .apply();
    }

    public void setIsSaveLocallyOnly(Context context, boolean isSaveLocallyOnly) {
        this.isSaveLocallyOnly = isSaveLocallyOnly;
        SharedPreferences pref = context.getSharedPreferences(AUTH_SHARED_PREFS, Context.MODE_PRIVATE);
        pref.edit()
            .putBoolean(IS_SAVELOCALLY_ONLY_KEY, isSaveLocallyOnly)
            .apply();
    }
}
