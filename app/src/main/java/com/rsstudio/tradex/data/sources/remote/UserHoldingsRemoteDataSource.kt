package com.rsstudio.tradex.data.sources.remote

import com.rsstudio.tradex.data.restclient.AppApiClientService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserHoldingsRemoteDataSource @Inject constructor(
    private val service: AppApiClientService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun fetchUserHoldings() = flow {
        emit(service.fetchUserHoldings())
    }.flowOn(dispatcher)
}