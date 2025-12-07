package com.osprey.home.navigation

import android.content.Context
import com.osprey.common.navigation.HomeNavigator
import com.osprey.core.extension.openActivity
import com.osprey.home.ui.adddevice.AddDeviceActivity
import com.osprey.home.ui.resetdevice.ResetDeviceActivity

class HomeNavigatorImpl() : HomeNavigator {
    override fun openAddDevice(context: Context) {
        return context.openActivity<AddDeviceActivity>()
    }
    override fun openResetDevice(context: Context) {
        return context.openActivity<ResetDeviceActivity>()
    }
}