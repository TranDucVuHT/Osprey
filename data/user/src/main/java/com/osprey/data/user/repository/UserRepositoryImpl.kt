package com.osprey.data.user.repository

import com.osprey.data.common.datasource.AppSharePrefs
import com.osprey.data.user.datasource.remote.interceptor.AuthDataSource
import com.osprey.domain.user.model.User
import com.osprey.domain.user.model.request.LoginRequest
import com.osprey.domain.user.repository.UserRepository
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val appSharePrefs: AppSharePrefs
) : UserRepository {

    override suspend fun getUserByEmail(email: String): User? {
        return null
    }

    override suspend fun getUserById(id: UUID): User? {
        return null
    }

    override suspend fun getCurrentUserEmail(): String? {
        return try {
            val user = authDataSource.getCurrentUser()
            user?.email
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun logout() {
        try {
            appSharePrefs.clearAuthData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun login(request: LoginRequest): User? {
        return try {
            val loginResponse = authDataSource.login(request.username, request.password)
            if (loginResponse != null) {
                appSharePrefs.authToken = loginResponse.token
                val currentUser = authDataSource.getCurrentUser()
                currentUser ?: loginResponse.user
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}