package com.osprey.main.navigation

import android.content.Context
import com.osprey.core.extension.openActivity
import com.osprey.main.ui.main.MainActivity
import com.osprey.common.navigation.MainNavigator
import com.wodox.domain.main.model.Constants

class MainNavigatorImpl : MainNavigator {
    override fun showMain(context: Context, isFirstLaunch: Boolean) {
        context.openActivity<MainActivity>(
            Constants.Intents.IS_FIRST_LAUNCH to isFirstLaunch
        )
    }

}
