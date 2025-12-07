package com.osprey.domain.user.usecase

import com.osprey.domain.base.BaseNoParamsUnsafeUseCase
import com.osprey.domain.user.repository.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseNoParamsUnsafeUseCase<Unit>() {
    override suspend fun execute() {
        return userRepository.logout()
    }
}