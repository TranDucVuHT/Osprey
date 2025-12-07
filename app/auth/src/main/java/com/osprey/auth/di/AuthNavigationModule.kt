package com.osprey.auth.di

import com.osprey.auth.navigation.AuthNavigatorImpl
import com.osprey.common.navigation.AuthNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthNavigationModule {
    @Provides
    @Singleton
    fun provideIntroNavigator(): AuthNavigator = AuthNavigatorImpl()
}