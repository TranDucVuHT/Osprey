package com.osprey.domain.user.usecase

import com.osprey.domain.base.BaseNoParamsUnsafeUseCase
import com.osprey.domain.user.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserEmail @Inject constructor(
    private val userRepository: UserRepository
) : BaseNoParamsUnsafeUseCase<String?>() {
    override suspend fun execute(): String? {
        return userRepository.getCurrentUserEmail()
    }
}