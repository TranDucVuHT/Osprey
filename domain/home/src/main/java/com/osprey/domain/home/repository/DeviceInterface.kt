package com.osprey.domain.home.repository

import com.osprey.domain.home.model.local.Device_Profile

interface DeviceRepository {
    fun getAllDevice(): List<Device_Profile>
}
