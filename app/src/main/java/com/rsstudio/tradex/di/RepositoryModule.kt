package com.rsstudio.tradex.di

import com.rsstudio.tradex.data.repositories.UserHoldingsRepository
import com.rsstudio.tradex.data.sources.local.UserHoldingsLocalDataSource
import com.rsstudio.tradex.data.sources.remote.UserHoldingsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun userHoldingsRepository(
        remoteDataSource: UserHoldingsRemoteDataSource,
        localDBDataSource: UserHoldingsLocalDataSource
    ) = UserHoldingsRepository(remoteDataSource, localDBDataSource)

}