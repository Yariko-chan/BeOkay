package com.gmail.f.d.ganeeva.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 25.08.2017 at 17:58.
 */

public class AuthDataModel implements DataModel {
    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
