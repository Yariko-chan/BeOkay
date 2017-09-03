package com.gmail.f.d.ganeeva.domain.interactions

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

class LoginUseCase : UseCase<AuthDomainModel, UserDomainModel>() {
    override fun buildUseCase(authDomainModel: AuthDomainModel): Observable<UserDomainModel>? {
        return RestService.getInstance()
                .login(convertAuthToData(authDomainModel))
                .map({data:UserDataModel -> convertUserToDomain(data)})
    }
//    var domainModel = RestService.getInstance()
//            .login(convertAuthToData(authDomainModel))
//            .map({
//                data:UserDataModel ->
//                if (data.errorMessage == null || data.errorMessage.equals("")) // if no error, return as usual
//                    convertUserToDomain(data)
//                else { // else throw an Error passed by Backendless
//                    throw convertUserToError(data) // this should be catched in onError
//                }
//            })
//    return domainModel


}
