package com.rsstudio.tradex.di

import com.rsstudio.tradex.data.local.dao.UserHoldingDao
import com.rsstudio.tradex.data.restclient.AppApiClientService
import com.rsstudio.tradex.data.sources.local.UserHoldingsLocalDataSource
import com.rsstudio.tradex.data.sources.remote.UserHoldingsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun userHoldingsRemoteDataSource(
        service: AppApiClientService
    ) = UserHoldingsRemoteDataSource(service)

    @Provides
    fun userHoldingsLocalDataSource(dao: UserHoldingDao) = UserHoldingsLocalDataSource(dao)
}