package com.rsstudio.tradex.data.restclient

import com.rsstudio.tradex.data.models.response.UserHoldingResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface AppApiClientService {
    @GET
    suspend fun fetchUserHoldings(): Flow<Response<UserHoldingResponse>>
}