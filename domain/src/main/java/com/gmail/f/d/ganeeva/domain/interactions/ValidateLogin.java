package com.gmail.f.d.ganeeva.domain.interactions;

import com.gmail.f.d.ganeeva.data.net.RestService;
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase;

import io.reactivex.Observable;

/**
 * Created by Diana on 09.09.2017 at 12:14.
 */

public class ValidateLogin extends UseCase<String, Boolean> {
    @Override
    public Observable<Boolean> buildUseCase(String userToken) {
        return RestService.getInstance()
            .validateLogin(userToken);
    }
}
