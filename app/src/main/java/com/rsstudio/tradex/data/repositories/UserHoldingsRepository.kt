package com.rsstudio.tradex.data.repositories

import com.rsstudio.tradex.data.local.entity.UserHoldingEntity
import com.rsstudio.tradex.data.sources.local.UserHoldingsLocalDataSource
import com.rsstudio.tradex.data.sources.remote.UserHoldingsRemoteDataSource
import com.rsstudio.tradex.presentation.home.UserHoldingsData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserHoldingsRepository @Inject constructor(
    private val remoteDataSource: UserHoldingsRemoteDataSource,
    private val localDataSource: UserHoldingsLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchUserHoldings(): Flow<List<UserHoldingsData>> = flow {
        val remoteResponse = remoteDataSource.fetchUserHoldings().firstOrNull()
        if (remoteResponse?.isSuccessful == true) {
            remoteResponse.body()?.let { response ->
                val entities = response.data.userHolding.map { holding ->
                    UserHoldingEntity(
                        symbol = holding.symbol,
                        quantity = holding.quantity,
                        ltp = holding.ltp,
                        avgPrice = holding.avgPrice,
                        close = holding.close
                    )
                }
                localDataSource.saveHoldings(entities)
            }
        }
        emitAll(localDataSource.getAllHoldings())
    }.flowOn(dispatcher)
}
