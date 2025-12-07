package com.osprey.domain.home.model.local

import java.util.UUID

data class Device(
    val id: UUID,
    val createdTime: Long,
    val additionalInfo: String?,
    val customerId: UUID?,
    val deviceProfileId: UUID,
    val deviceData: String?,
    val type: String?,
    val name: String?,
    val label: String?,
    val tenantId: UUID,
    val firmwareId: UUID?,
    val softwareId: UUID?,
    val externalId: UUID?,
    val version: Long
)
