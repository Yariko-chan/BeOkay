package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Diana on 16.08.2017.
 */

public interface RestAPI {
    @POST("users/login")
    Observable<UserDataModel> login(@Body AuthDataModel authData);
}
