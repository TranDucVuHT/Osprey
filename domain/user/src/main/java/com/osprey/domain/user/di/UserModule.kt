package com.osprey.domain.user.di

import com.osprey.domain.user.repository.UserRepository
import com.osprey.domain.user.usecase.GetCurrentUserEmail
import com.wodox.domain.user.usecase.GetUserByEmailUseCase
import com.osprey.domain.user.usecase.GetUserById
import com.osprey.domain.user.usecase.LoginUseCase
import com.osprey.domain.user.usecase.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {



    @Provides
    @Singleton
    fun provideGetUserByEmailUseCase(
        userRepository: UserRepository,
    ): GetUserByEmailUseCase {
        return GetUserByEmailUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetCurrentUserEmail(
        userRepository: UserRepository,
    ): GetCurrentUserEmail {
        return GetCurrentUserEmail(userRepository)
    }

    @Singleton
    @Provides
    fun provideSingoutUseCase(
        userRepository: UserRepository,
    ): SignOutUseCase {
        return SignOutUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserById(
        userRepository: UserRepository,
    ): GetUserById {
        return GetUserById(userRepository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        userRepository: UserRepository,
    ): LoginUseCase {
        return LoginUseCase(userRepository)
    }
}


