package com.osprey.domain.home.model.local

import java.util.UUID

data class Device_Profile(
    val id: UUID,
    val createdTime: Long,
    val name: String? = null,
    val type: String? = null,
    val image: Int? = null,
    val transportType: String? = null,
    val provisionType: String? = null,
    val profileData: Map<String, Any>? = null,
    val description: String? = null,
    val isDefault: Boolean? = null,
    val tenantId: UUID? = null,
    val firmwareId: UUID? = null,
    val softwareId: UUID? = null,
    val defaultRuleChainId: UUID? = null,
    val defaultDashboardId: UUID? = null,
    val defaultQueueName: String? = null,
    val provisionDeviceKey: String? = null,
    val defaultEdgeRuleChainId: String? = null,
    val externalId: String? = null,
    val version: Long? = null,
    val dpList: Map<String, Any>? = null
)
