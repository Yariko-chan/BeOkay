package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryDraftDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Diana on 16.08.2017.
 */

public class RestService {
    public static final String BASE_URL = "https://api.backendless.com/A37125EB-0082-7D66-FF25-929133695B00/4403AEBD-3897-3B7A-FF2B-35CDD8DE1200/";
    private static RestService instance;

    private RestAPI restAPI;

    public RestService(RestAPI restAPI) {
        this.restAPI = restAPI;
    }

//    private RestService() {
//        init();
//    }
//
//    public static RestService getInstance() {
//        if (null == instance) {
//            instance = new RestService();
//        }
//        return instance;
//    }
//
//    /**
//     * settings for retrofit
//     */
//    public void init() {
//        // замена HttpConnection с плюшками. низкоуровневое взаимодействие с интернетом. Тут можно логирование и ввсякие настройки
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(10, TimeUnit.SECONDS) // ограничение чтобы польхователь не ждал полчаса
//                .connectTimeout(10, TimeUnit.SECONDS) // если сервер недоступен или ещё что-то не так
//                .addInterceptor(logging) // logging
//                .build();
//
//        Gson gson = new GsonBuilder() // настройки парсинга json
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL) // базовая ссылка, домен + параметры
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // исп-е rx
//                .addConverterFactory(GsonConverterFactory.create(gson)) // как парсить данные
//                .client(okHttpClient) // как получать доступ к интернету; по умолчанию использует стандартное, okHttp даёт возможность настроить
//                .build();
//
//        restAPI = retrofit.create(RestAPI.class);
//    }

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

    public Observable<DiaryDraftDataModel[]> getDiaryDrafts(int pageSize, int offset) {
        Observable<DiaryDraftDataModel[]> drafts = Observable.just(new DiaryDraftDataModel[0]);
        return restAPI.getDiaryDrafts(pageSize, offset);
    }

    public Observable<DiaryEntryDataModel[]> getDiaryEntries(String email) {
        String whereClause = createCondition(email);
        return restAPI.getDiaryEntries(whereClause);
    }

    // creates String for querying diary Entries by email
    // e. g. "userId.email='someemail@gmail.com'"
    private String createCondition(String email) {
        StringBuilder builder = new StringBuilder("");
        builder.append("userId.email='")
            .append(email)
            .append("'");
        return builder.toString();
    }
}
