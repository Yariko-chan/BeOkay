package com.gmail.f.d.ganeeva.beokay.general.utils

import android.content.Context
import android.text.TextUtils
import com.gmail.f.d.ganeeva.beokay.R
import io.reactivex.annotations.NonNull
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Diana on 27.09.2017 at 12:20.
 */
fun getErrorMessage(context: Context, @NonNull e: Throwable): String {
    return if (e is HttpException) {
        when (e.code()) {
            401 -> context.getString(R.string.msg_incorrect_auth_data)
            400 -> context.getString(R.string.msg_choose_other_account)
            409 -> context.getString(R.string.msg_email_already_registered)
            else -> e.message ?: context.getString(R.string.msg_unknown_error)
        }
    } else if (e is SocketTimeoutException) {
        context.getString(R.string.msg_connection_timed_out)
    } else if (e is UnknownHostException) {
        context.getString(R.string.msg_network_error)
    } else {
        e.message ?: context.getString(R.string.msg_unknown_error)
    }
}