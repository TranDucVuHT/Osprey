package com.osprey.domain.home.di

import com.osprey.domain.home.repository.DeviceRepository
import com.osprey.domain.home.usecase.GetAllDeviceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeUseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(
        deviceRepository: DeviceRepository,
    ): GetAllDeviceUseCase {
        return GetAllDeviceUseCase(deviceRepository)
    }

}