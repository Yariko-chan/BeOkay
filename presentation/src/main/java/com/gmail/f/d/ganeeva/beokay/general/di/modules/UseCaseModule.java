package com.gmail.f.d.ganeeva.beokay.general.di.modules;

import com.gmail.f.d.ganeeva.data.net.RestService;
import com.gmail.f.d.ganeeva.domain.interactions.auth.LoginUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.auth.RecoverPasswordUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.auth.RegisterUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.auth.ValidateLoginUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.diary.GetDiaryDraftsUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.diary.GetDiaryEntriesUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.diary.SaveDiaryEntryUC;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    public RegisterUseCase provideRegisterUC(RestService restService) {
        return new RegisterUseCase(restService);
    }

    @Provides
    public LoginUseCase provideLoginUC(RestService restService) {
        LoginUseCase useCase = new LoginUseCase(restService);
        return useCase;
    }

    @Provides
    public RecoverPasswordUseCase provideRecoverPasswordUC(RestService restService) {
        return new RecoverPasswordUseCase(restService);
    }

    @Provides
    public ValidateLoginUseCase provideValidateLoginUC(RestService restService) {
        return new ValidateLoginUseCase(restService);
    }

    @Provides
    public GetDiaryDraftsUseCase provideDiaryDraftsUC(RestService restService) {
        return new GetDiaryDraftsUseCase(restService);
    }

    @Provides
    public GetDiaryEntriesUseCase provideDiaryEntriesUC(RestService restService) {
        return new GetDiaryEntriesUseCase(restService);
    }

    @Provides
    public SaveDiaryEntryUC provideSaveDiaryUC(RestService restService) {
        return new SaveDiaryEntryUC(restService);
    }
}
