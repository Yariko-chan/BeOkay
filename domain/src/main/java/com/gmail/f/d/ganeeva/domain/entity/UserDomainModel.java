package com.gmail.f.d.ganeeva.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 25.08.2017 at 18:12.
 */

public class UserDomainModel implements DomainModel {

    private String name;
    private String email;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}