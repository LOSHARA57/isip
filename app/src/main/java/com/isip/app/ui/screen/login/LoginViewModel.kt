package com.isip.app.ui.screen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.isip.app.R
import com.isip.app.ui.model.api.Api
import com.isip.app.utils.ToastHelper
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel: ViewModel() {

    companion object {
        var name = ""
        var post = ""

        var regLogin = MutableStateFlow("")
        var regPass = MutableStateFlow("")

    }

    fun login(login: String, pass: String): Boolean {

        if (login == regLogin.value && regPass.value == pass) {
            return true
        }

        val user = Api().login(login, pass)
        if (user != null) {
            name = user.name
            post = user.post
            return true
        }
        return false
    }

    fun registration(context: Context, login: String, pass: String, repPass: String): Boolean {
        if (pass == "") {
            ToastHelper().show( context, context.resources.getString(R.string.toastEnterPass))
            return false
        }

        if (pass != repPass) {
            ToastHelper().show( context,context.resources.getString(R.string.toastPassDoNotMutch))
            return false
        }

        regLogin.value = login
        regPass.value = repPass
        return true
    }
}