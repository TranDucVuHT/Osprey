package com.osprey.auth.navigation

import android.content.Context
import com.osprey.auth.model.Constants
import com.osprey.auth.ui.sigin.SignInActivity
import com.osprey.auth.ui.welcome.WelcomeActivity
import com.osprey.core.extension.openActivity
import com.osprey.common.navigation.AuthNavigator

class AuthNavigatorImpl : AuthNavigator {
    override fun showWelcome(
        context: Context
    ) {
        context.openActivity<WelcomeActivity>()
    }

    override fun showSignIn(context: Context, isShowSignUp: Boolean) {
        context.openActivity<SignInActivity>(
            Constants.Intents.IS_SHOW_SIGN_UP to isShowSignUp
        )
    }

}