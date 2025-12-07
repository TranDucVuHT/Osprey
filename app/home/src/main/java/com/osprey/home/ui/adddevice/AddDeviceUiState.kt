package com.osprey.home.ui.adddevice

import com.osprey.domain.home.model.local.Device_Profile

data class AddDeviceUiState (
    var deviceProfile : List<Device_Profile> = emptyList()
)