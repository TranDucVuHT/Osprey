package com.osprey.home.ui.resetdevice

import com.osprey.core.base.activity.BaseActivity
import com.osprey.home.ui.adddevice.AddDevicViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.osprey.home.R
import com.osprey.home.databinding.ActivityResetDeviceLayoutBinding

@AndroidEntryPoint
class ResetDeviceActivity : BaseActivity<ActivityResetDeviceLayoutBinding, AddDevicViewModel>(
    AddDevicViewModel::class) {

    override fun layoutId(): Int  = R.layout.activity_reset_device_layout

    override fun initialize() {
        setupUi()
        setupAction()
    }

    private fun setupUi(){

    }

    private fun setupAction(){

    }
}