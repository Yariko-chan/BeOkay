package com.gmail.f.d.ganeeva.beokay.general.di.modules;

import com.gmail.f.d.ganeeva.data.net.RestAPI;
import com.gmail.f.d.ganeeva.data.net.RestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {

    @Singleton
    @Provides
    public RestService provideRestService(RestAPI restAPI) {
        RestService restService = new RestService(restAPI);
        return restService;
    }

    @Provides
    public RestAPI provideRestAPI(Retrofit retrofit) {
        return retrofit.create(RestAPI.class);
    }

    @Provides
    public Retrofit provideRetrofit(@Named ("BASE_URL") String BASE_URL,
                                   CallAdapter.Factory callAdapterFactory,
                                   Converter.Factory converterFactory,
                                   OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL) // базовая ссылка, домен + параметры
                .addCallAdapterFactory(callAdapterFactory) // исп-е rx
                .addConverterFactory(converterFactory) // как парсить данные
                .client(client) // как получать доступ к интернету; по умолчанию использует стандартное, okHttp даёт возможность настроить
                .build();
    }

    @Named ("BASE_URL")
    @Provides
    public String provideBaseUrl() {
        return RestService.BASE_URL;
    }

    @Provides
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Interceptor logging) {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS) // ограничение чтобы польхователь не ждал полчаса
                .connectTimeout(10, TimeUnit.SECONDS) // если сервер недоступен или ещё что-то не так
                .addInterceptor(logging) // logging
                .build();
    }

    @Provides
    public Interceptor provideInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
