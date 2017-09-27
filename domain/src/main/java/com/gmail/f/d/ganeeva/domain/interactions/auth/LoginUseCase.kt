package com.gmail.f.d.ganeeva.domain.interactions.auth

import com.gmail.f.d.ganeeva.data.entity.UserDataModel
import io.reactivex.Observable

import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.entity.AuthDomainModel
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase
import com.gmail.f.d.ganeeva.domain.utils.convertAuthToData
import com.gmail.f.d.ganeeva.domain.utils.convertUserToDomain


/**
 * Created by Diana on 25.08.2017 at 18:09.
 */

class LoginUseCase(internal var restService: RestService) : UseCase<AuthDomainModel, UserDomainModel>() {

    override fun buildUseCase(authDomainModel: AuthDomainModel): Observable<UserDomainModel>? {
        return restService
                .login(convertAuthToData(authDomainModel))
                .map({data:UserDataModel -> convertUserToDomain(data)})
    }


}
