package com.osprey.domain.home.usecase

import com.osprey.domain.base.BaseNoParamsUnsafeUseCase
import com.osprey.domain.home.model.local.Device_Profile
import com.osprey.domain.home.repository.DeviceRepository

class GetAllDeviceUseCase(
    private val repo: DeviceRepository
) : BaseNoParamsUnsafeUseCase<List<Device_Profile>>() {
    override suspend fun execute(): List<Device_Profile> {
        return repo.getAllDevice()
    }
}
