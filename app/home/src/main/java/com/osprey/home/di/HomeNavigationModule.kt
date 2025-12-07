package com.wodox.di

import com.osprey.common.navigation.HomeNavigator
import com.osprey.home.navigation.HomeNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeNavigationModule {
    @Provides
    @Singleton
    fun provideIntroNavigator(): HomeNavigator = HomeNavigatorImpl()
}