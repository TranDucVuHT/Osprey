package com.osprey.intro.navigation

import android.content.Context
import com.osprey.core.extension.openActivity
import com.wodox.common.navigation.IntroNavigator
import com.osprey.intro.ui.splash.SplashActivity

class IntroNavigatorImpl: IntroNavigator {
    override fun openSplash(context: Context, isMoveToForeground: Boolean) {
        return context.openActivity<SplashActivity>(
        )
    }
}