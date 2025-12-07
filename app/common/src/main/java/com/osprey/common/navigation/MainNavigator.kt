package com.osprey.common.navigation

import android.content.Context

interface MainNavigator {
    fun showMain(context: Context, isFirstLaunch: Boolean)

}