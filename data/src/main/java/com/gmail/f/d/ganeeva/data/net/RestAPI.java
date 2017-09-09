package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Diana on 16.08.2017.
 */

public interface RestAPI {
    @POST("users/login")
    Observable<UserDataModel> login(@Body AuthDataModel authData);

    @POST("users/register")
    Observable<UserDataModel> register(@Body UserDataModel userData);

    @GET("users/restorepassword/{identity}")
    Observable<Response<Void>> recoverPassword(@Path("identity") String login);

    @GET("users/isvalidusertoken/{userToken}")
    Observable<Boolean> validateLogin(@Path("userToken") String userToken);
}
