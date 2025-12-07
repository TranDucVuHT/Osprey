package com.osprey.data.home.di

import com.osprey.data.home.repository.DeviceRepositoryImpl
import com.osprey.domain.home.repository.DeviceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDeviceRepository(
    ): DeviceRepository {
        return DeviceRepositoryImpl()
    }

//    @Provides
//    @Singleton
//    fun provideWeatherRepository(
//        weatherApi: WeatherApiService
//    ): WeatherRepositoryImpl {
//        return WeatherRepositoryImpl(weatherApi)
//    }
}
