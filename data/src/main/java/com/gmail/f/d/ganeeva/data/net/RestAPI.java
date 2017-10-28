package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryDraftDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("data/DiaryDrafts")
    Observable<DiaryDraftDataModel[]> getDiaryDrafts(@Query("pageSize") int pageSize, @Query("offset") int offset);

    /**
     * save diary entry
     * @param token saved to SharedPrefs after login
     * @param diaryEntry
     * @return
     */

    @POST("data/diaryEntries")
    Observable<DiaryEntryDataModel> saveDiaryEntry(@Header("user-token") String token, @Body DiaryEntryDataModel diaryEntry);

    /**
     * return all rows with ownerId of currently authenticated user
     * @param token saved to SharedPrefs after login
     */
    @GET("data/diaryEntries?sortBy=entryDate%20desc")
    Observable<DiaryEntryDataModel[]> getDiaryEntries(@Header("user-token") String token);
}
