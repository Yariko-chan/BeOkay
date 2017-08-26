package com.gmail.f.d.ganeeva.domain.interactions

import io.reactivex.Observable

import com.gmail.f.d.ganeeva.data.entity.AuthDataModel
import com.gmail.f.d.ganeeva.data.entity.UserDataModel
import com.gmail.f.d.ganeeva.data.net.RestService
import com.gmail.f.d.ganeeva.domain.entity.AuthDomainModel
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel
import com.gmail.f.d.ganeeva.domain.interactions.base.UseCase

/**
 * Created by Diana on 25.08.2017 at 18:09.
 */

class LoginUseCase : UseCase<AuthDomainModel, UserDomainModel>() {
    override fun buildUseCase(authDomainModel: AuthDomainModel): Observable<UserDomainModel>? {
        return RestService.getInstance()
                .login(convertToData(authDomainModel))
                .map({data -> convertToDomain(data)})
    }

    private fun convertToDomain(dataModel: UserDataModel): UserDomainModel {
        val domainModel = UserDomainModel()
        domainModel.name = dataModel.name
        domainModel.id = dataModel.id
        domainModel.email = dataModel.email
        return domainModel
    }

    private fun convertToData(domainModel: AuthDomainModel): AuthDataModel {
        val dataModel = AuthDataModel()
        dataModel.login = domainModel.login
        dataModel.password = domainModel.password
        return dataModel
    }
}
