package com.gmail.f.d.ganeeva.domain.interactions

import com.gmail.f.d.ganeeva.data.net.RestService
import io.reactivex.Observable

import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase
import com.gmail.f.d.ganeeva.domain.utils.convertUserToData
import com.gmail.f.d.ganeeva.domain.utils.convertUserToDomain

/**
 * Created by Diana on 27.08.2017 at 18:51.
 */
class RegisterUseCase : UseCase<UserDomainModel, UserDomainModel>() {

    override fun buildUseCase(userDomainModel: UserDomainModel) : Observable<UserDomainModel> {
        return RestService.getInstance()
                .register(convertUserToData(userDomainModel))
                .map {dataModel -> convertUserToDomain(dataModel)}
    }
}