package com.osprey.home.ui.adddevice

import com.osprey.domain.home.model.local.Device_Profile

sealed class AddDeviceUiAction {
    data class AddBluetoothDevice(val device: Device_Profile) : AddDeviceUiAction()
}
