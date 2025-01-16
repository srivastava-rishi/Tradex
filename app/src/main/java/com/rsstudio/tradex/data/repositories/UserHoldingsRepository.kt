package com.rsstudio.tradex.data.repositories

import com.rsstudio.tradex.data.sources.local.UserHoldingsLocalDataSource
import com.rsstudio.tradex.data.sources.remote.UserHoldingsRemoteDataSource
import javax.inject.Inject

class UserHoldingsRepository @Inject constructor(
    private val remoteDataSource: UserHoldingsRemoteDataSource,
    private val localDBDataSource: UserHoldingsLocalDataSource
) {
    fun fetchUserHoldings() = remoteDataSource.fetchUserHoldings()
}