package com.gmail.f.d.ganeeva.beokay.general.di;

import com.gmail.f.d.ganeeva.beokay.articles.ArticlesViewModel;
import com.gmail.f.d.ganeeva.beokay.authorization.login.LoginViewModel;
import com.gmail.f.d.ganeeva.beokay.authorization.password_recovery.RecoverPasswordViewModel;
import com.gmail.f.d.ganeeva.beokay.authorization.register.RegisterViewModel;
import com.gmail.f.d.ganeeva.beokay.diary.add.AddDiaryEntryViewModel;
import com.gmail.f.d.ganeeva.beokay.diary.view.DiaryFragment;
import com.gmail.f.d.ganeeva.beokay.diary.view.DiaryViewModel;
import com.gmail.f.d.ganeeva.beokay.diary.add.drafts.DiaryDraftsViewModel;
import com.gmail.f.d.ganeeva.beokay.general.HomeActivity;
import com.gmail.f.d.ganeeva.beokay.general.LauncherActivity;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.ApplicationModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.RestModule;
import com.gmail.f.d.ganeeva.beokay.general.di.modules.UseCaseModule;
import com.gmail.f.d.ganeeva.beokay.settings.SettingsFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RestModule.class, UseCaseModule.class, ApplicationModule.class})
@Singleton
public interface AppComponent {

    void inject(LoginViewModel viewModel);

    void inject(RegisterViewModel viewModel);

    void inject(RecoverPasswordViewModel viewModel);

    void inject(@NotNull LauncherActivity activity);

    void inject(DiaryDraftsViewModel diaryDraftsViewModel);

    void inject(DiaryViewModel diaryViewModel);

    void inject(SettingsFragment settingsFragment);

    void inject(AddDiaryEntryViewModel settingsFragment);

    void inject(HomeActivity homeActivity);

    void inject(ArticlesViewModel articlesViewModel);
}
