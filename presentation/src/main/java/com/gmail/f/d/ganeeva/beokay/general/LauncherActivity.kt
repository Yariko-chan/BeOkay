package com.gmail.f.d.ganeeva.beokay.general

import android.app.Activity
import android.os.Bundle

import com.gmail.f.d.ganeeva.beokay.R
import com.gmail.f.d.ganeeva.beokay.authorization.AuthorizationActivity
import com.gmail.f.d.ganeeva.domain.interactions.ValidateLogin

import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver

class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val auth = Authorization.getInstance(this)
        if (auth.isSaveLocallyOnly) {
            goHomeActivity()
            return
        }

        if (!auth.isStayLogged || !auth.isAuthorized) {
            goAuthActivity()
            return
        }

        val token = auth.userToken
        if (null == token || token == "") {
            goAuthActivity()
            return
        }

        ValidateLogin().execute(token, object : DisposableObserver<Boolean>() {
            override fun onNext(valid: Boolean) {
                if (valid) {
                    goHomeActivity()
                    return
                } else {
                    auth.cleanToken(this@LauncherActivity)
                    auth.setIsAuthorized(this@LauncherActivity, false)
                    goAuthActivity()
                    return
                }
            }

            override fun onError(@NonNull e: Throwable) {
                // TODO: handle errors here
                goAuthActivity()
                return
            }

            override fun onComplete() {}
        });

    }

    private fun goAuthActivity() {
        AuthorizationActivity.show(this);
        this.finish()
        return
    }

    private fun goHomeActivity() {
        HomeActivity.show(this)
        this.finish()
        return
    }
}
