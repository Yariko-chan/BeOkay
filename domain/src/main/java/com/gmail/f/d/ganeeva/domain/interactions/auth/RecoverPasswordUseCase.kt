package com.gmail.f.d.ganeeva.domain.interactions.auth

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Diana on 29.08.2017 at 11:32.
 */

class RecoverPasswordUseCase(internal var restService: RestService) : UseCase<String, Response<Void>>() {

    override fun buildUseCase(s: String): Observable<Response<Void>> {
        return restService
                .recoverPassword(s)
    }
}
