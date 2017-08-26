package com.gmail.f.d.ganeeva.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 25.08.2017 at 17:40.
 */

public class UserDataModel implements DataModel {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("objectId")
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
