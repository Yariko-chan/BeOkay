package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.ArticleDataModel;
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
        return restAPI.getDiaryDrafts(pageSize, offset);
    }

    public Observable<DiaryEntryDataModel[]> getDiaryEntries(String token) {
        return restAPI.getDiaryEntries(token);
    }

    public Observable<DiaryEntryDataModel> saveDiaryEntry(DiaryEntryDataModel diaryEntry, String token) {
        return restAPI.saveDiaryEntry(token, diaryEntry);
    }

    public Observable<ArticleDataModel[]> getArticles(int pageSize, int offset) {
        return restAPI.getArticles(pageSize, offset);
    }
}
