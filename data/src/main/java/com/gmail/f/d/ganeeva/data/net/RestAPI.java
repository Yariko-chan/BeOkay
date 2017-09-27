package com.gmail.f.d.ganeeva.data.net;

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryDraftDataModel;
import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel;
import com.gmail.f.d.ganeeva.data.entity.UserDataModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    /** posting diary entry with relation to user:
     * 1. add entry POST "/data/diaryEntries" @Body Json object
     * {
     "entries": "{json Array}",
     "EntryDate": 1505077200000
     }
     * 2. add relation POST "/data/diaryEntries/{entry objectId}/userId @Body ["[user objectId]"]
     */
    @POST("/data/diaryEntries")
    Observable<DiaryEntryDataModel> saveDiaryEntry(@Body DiaryEntryDataModel diaryEntry);

    @POST("/data/diaryEntries/{diaryEntryId}/userId")
    Observable<Integer> bindUserToDiaryEntry(@Path("diaryEntryId") String diaryEntryId, @Body String[] userId );

    // get all data entries related to user with such email
    // data/diaryEntries?where=userId.email='lililala1991@gmail.com'
    @GET("data/diaryEntries")
    Observable<DiaryEntryDataModel[]> getDiaryEntries(@Query("where") String condition);
}
