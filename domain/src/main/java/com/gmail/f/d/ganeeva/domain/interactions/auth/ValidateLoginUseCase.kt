package com.gmail.f.d.ganeeva.domain.interactions.auth

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase

import io.reactivex.Observable

/**
 * Created by Diana on 09.09.2017 at 12:14.
 */

class ValidateLoginUseCase(internal var restService: RestService) : UseCase<String, Boolean>() {

    override fun buildUseCase(userToken: String): Observable<Boolean> {
        return restService.validateLogin(userToken)
    }
}
