package com.gmail.f.d.ganeeva.domain.utils

import android.util.Log
import com.gmail.f.d.ganeeva.data.entity.AuthDataModel
import com.gmail.f.d.ganeeva.data.entity.DiaryEntryDataModel
import com.gmail.f.d.ganeeva.data.entity.UserDataModel
import com.gmail.f.d.ganeeva.domain.entity.AuthDomainModel
import com.gmail.f.d.ganeeva.domain.entity.DiaryEntryDomainModel
import com.gmail.f.d.ganeeva.domain.entity.UserDomainModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Diana on 27.08.2017 at 19:02.
 */

fun convertUserToDomain(dataModel: UserDataModel): UserDomainModel {
    val domainModel = UserDomainModel()
    domainModel.name = dataModel.name
    domainModel.id = dataModel.id
    domainModel.email = dataModel.email
    domainModel.userToken = dataModel.userToken
    return domainModel
}

fun convertUserToData(domainModel: UserDomainModel) : UserDataModel {
    val dataModel = UserDataModel()
    dataModel.email = domainModel.email
    dataModel.password = domainModel.password
    dataModel.name = domainModel.name
    dataModel.id = domainModel.id
    return dataModel
}

fun convertAuthToData(domainModel: AuthDomainModel): AuthDataModel {
    val dataModel = AuthDataModel()
    dataModel.login = domainModel.login
    dataModel.password = domainModel.password
    return dataModel
}

fun convertDiaryEntryDataToDomain(dataModel: DiaryEntryDataModel): DiaryEntryDomainModel {
    val domainModel = DiaryEntryDomainModel()
    // date
    // timestamp -> string(format 22/3/2017)
    val date =  Date(dataModel.dateTimestamp);
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val dateString = sdf.format(date)
    domainModel.entryDate = dateString

    // items
    domainModel.items = dataModel.itemsJson;

    return domainModel
}