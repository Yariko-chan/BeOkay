package com.gmail.f.d.ganeeva.beokay.general.di.modules;

import com.gmail.f.d.ganeeva.data.net.RestService;
import com.gmail.f.d.ganeeva.domain.interactions.LoginUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.RecoverPasswordUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.RegisterUseCase;
import com.gmail.f.d.ganeeva.domain.interactions.ValidateLoginUseCase;

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
}
