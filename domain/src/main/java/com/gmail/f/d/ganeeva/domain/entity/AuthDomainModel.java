package com.gmail.f.d.ganeeva.domain.entity;

/**
 * Created by Diana on 25.08.2017 at 18:06.
 */

public class AuthDomainModel implements DomainModel {

    private String login;
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
