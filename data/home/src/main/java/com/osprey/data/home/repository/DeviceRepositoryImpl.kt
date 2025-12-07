package com.osprey.data.home.repository

import com.osprey.domain.home.model.local.Device_Profile
import com.osprey.domain.home.repository.DeviceRepository
import com.osprey.resources.R

class DeviceRepositoryImpl : DeviceRepository {
    override fun getAllDevice(): List<Device_Profile> {
        return listOf(
            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Curtains (other)",
                type = "other",
                image = R.drawable.ic_curtain,
                transportType = null,
                provisionType = null,
                profileData = null,
                description = null,
                isDefault = false,
                tenantId = uuid(),
                firmwareId = null,
                softwareId = null,
                defaultRuleChainId = null,
                defaultDashboardId = null,
                defaultQueueName = null,
                provisionDeviceKey = null,
                defaultEdgeRuleChainId = null,
                externalId = null,
                version = 1L,
                dpList = null
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Curtains (BLE)",
                type = "BLE",
                image = R.drawable.ic_curtain,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Body Fat Scale (BLE)",
                type = "BLE",
                image = R.drawable.ic_body_fat,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Switch (BLE+Wi-Fi)",
                type = "BLE+Wi-Fi",
                image = R.drawable.ic_curtain,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Socket (Zigbee)",
                type = "Zigbee",
                image = R.drawable.ic_smart_plug,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Socket (Wi-Fi)",
                type = "Wi-Fi",
                image = R.drawable.ic_smart_plug,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Temperature & Humidity Sensor (BLE+Wi-Fi)",
                type = "BLE+Wi-Fi",
                image = R.drawable.ic_smart_plug,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Wireless Gateway (BLE)",
                type = "BLE",
                image = R.drawable.ic_curtain,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Light Source (BLE+Wi-Fi)",
                type = "BLE+Wi-Fi",
                image = R.drawable.ic_light,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Smart Rope Skipping (BLE)",
                type = "BLE",
                image = R.drawable.ic_smart_rope,
                tenantId = uuid(),
                version = 1L
            ),

            Device_Profile(
                id = uuid(),
                createdTime = now(),
                name = "Curtain (Wi-Fi)",
                type = "Wi-Fi",
                image = R.drawable.ic_curtain,
                tenantId = uuid(),
                version = 1L
            )
        )
    }


    private fun uuid() = java.util.UUID.randomUUID()
    private fun now() = System.currentTimeMillis()

}
