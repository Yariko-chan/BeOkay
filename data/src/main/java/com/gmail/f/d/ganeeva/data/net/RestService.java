package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diana on 16.08.2017.
 */

public class RestService {
    public static final String BASE_URL = "https://api.backendless.com/A37125EB-0082-7D66-FF25-929133695B00/4403AEBD-3897-3B7A-FF2B-35CDD8DE1200/";
    private static RestService instance;

    private RestAPI restAPI;

    @Inject
    private HttpLoggingInterceptor logging;

    private RestService() {
        init();
    }

    public static RestService getInstance() {
        if (null == instance) {
            instance = new RestService();
//            BeOkayApplication.appComponent.build();
        }
        return instance;
    }

    /**
     * settings for retrofit
     */
    public void init() {
        // замена HttpConnection с плюшками. низкоуровневое взаимодействие с интернетом. Тут можно логирование и ввсякие настройки
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS) // ограничение чтобы польхователь не ждал полчаса
                .connectTimeout(10, TimeUnit.SECONDS) // если сервер недоступен или ещё что-то не так
                .addInterceptor(logging) // logging
                .build();

        Gson gson = new GsonBuilder() // настройки парсинга json
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) // базовая ссылка, домен + параметры
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // исп-е rx
                .addConverterFactory(GsonConverterFactory.create(gson)) // как парсить данные
                .client(okHttpClient) // как получать доступ к интернету; по умолчанию использует стандартное, okHttp даёт возможность настроить
                .build();

        restAPI = retrofit.create(RestAPI.class);
    }

    public Observable<UserDataModel> login(AuthDataModel authData) {
        return restAPI.login(authData);
    }

    public Observable<UserDataModel> register(UserDataModel userData) {
        return restAPI.register(userData);
    }

    public Observable<Response<Void>> recoverPassword(String login) {
        return restAPI.recoverPassword(login);
    }

    public Observable<Boolean> validateLogin(String userToken) {
        return restAPI.validateLogin(userToken);
    }
}
