package com.osprey.domain.user.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class User(
    @SerializedName("id")
    val id: EntityId? = null,

    @SerializedName("createdTime")
    val createdTime: Long = System.currentTimeMillis(),

    @SerializedName("tenantId")
    val tenantId: EntityId? = null,

    @SerializedName("customerId")
    val customerId: EntityId? = null,

    @SerializedName("email")
    val email: String = "",

    @SerializedName("authority")
    val authority: String = "",

    @SerializedName("firstName")
    val firstName: String? = null,

    @SerializedName("lastName")
    val lastName: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("version")
    val version: Long = 1L,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("additionalInfo")
    val additionalInfo: String? = null
) {
    // Convert to UUID
    fun getUserIdAsUUID(): UUID? = try {
        id?.id?.let { UUID.fromString(it) }
    } catch (e: Exception) {
        null
    }

    fun getTenantIdAsUUID(): UUID? = try {
        tenantId?.id?.let { UUID.fromString(it) }
    } catch (e: Exception) {
        null
    }

    fun getCustomerIdAsUUID(): UUID? = try {
        customerId?.id?.let { UUID.fromString(it) }
    } catch (e: Exception) {
        null
    }

    fun getFullName(): String {
        return when {
            !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() -> "$firstName $lastName"
            !firstName.isNullOrEmpty() -> firstName
            !lastName.isNullOrEmpty() -> lastName
            !name.isNullOrEmpty() -> name
            else -> email
        }
    }
}

data class EntityId(
    @SerializedName("entityType")
    val entityType: String? = null,

    @SerializedName("id")
    val id: String? = null
)