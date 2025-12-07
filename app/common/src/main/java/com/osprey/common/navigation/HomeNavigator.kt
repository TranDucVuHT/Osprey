package com.osprey.common.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager

interface HomeNavigator {
    fun openAddDevice(context: Context)

 //   fun showTurnOnBluetooth(fragmentManager: FragmentManager)

    fun openResetDevice(context: Context)
}