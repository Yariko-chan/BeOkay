package com.gmail.f.d.ganeeva.beokay.diary.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.preference.PreferenceManager;
import android.util.Log;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseViewModel;
import com.gmail.f.d.ganeeva.beokay.diary.add.AddDiaryEntryFragment;
import com.gmail.f.d.ganeeva.beokay.diary.view.list_item.DiaryItemViewHolder;
import com.gmail.f.d.ganeeva.beokay.general.Authorization;
import com.gmail.f.d.ganeeva.beokay.general.BeOkayApplication;
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel;
import com.gmail.f.d.ganeeva.domain.interactions.diary.GetDiaryEntriesUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.gmail.f.d.ganeeva.beokay.general.utils.ErrorsHandlingKt.getErrorMessage;

/**
 * Created by Diana on 26.09.2017 at 15:33.
 */

public class DiaryViewModel implements BaseViewModel {
    private static final String TAG = DiaryViewModel.class.getSimpleName();

    public DiaryListAdapter<DiaryItemViewHolder> adapter = new DiaryListAdapter<>();

    @Inject GetDiaryEntriesUseCase useCase;
    @Inject Context applicationContext;

    public ObservableBoolean progress = new ObservableBoolean(true);
    public ObservableField<String> error = new ObservableField<>("");

    @Override
    public void init() {
        BeOkayApplication.appComponent.inject(this);
    }

    @Override
    public void release() {
        useCase.dispose();
    }

    @Override
    public void resume() {
        String token =  Authorization.getInstance(applicationContext).getUserToken();
        useCase.execute(token, new DisposableObserver<List<? extends DiaryEntryDomainModel>>() {
            @Override
            public void onNext(@NonNull List<? extends DiaryEntryDomainModel> diaryEntryList) {
                Log.d(TAG, "Diary entries received, count = " + diaryEntryList.size() );
                progress.set(false);
                ArrayList<DiaryEntryDomainModel> list = new ArrayList<>(diaryEntryList);
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                progress.set(false);
                error.set(getErrorMessage(applicationContext, e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pause() {}
}
