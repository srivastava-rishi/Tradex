package com.rsstudio.tradex.data.sources.local

import com.rsstudio.tradex.data.local.dao.UserHoldingDao
import com.rsstudio.tradex.data.local.entity.UserHoldingEntity
import com.rsstudio.tradex.presentation.home.UserHoldingsData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserHoldingsLocalDataSource @Inject constructor(
    private val dao: UserHoldingDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAllHoldings() = dao.getAllHoldings().map { entities ->
        entities.map { entity ->
            UserHoldingsData(
                symbol = entity.symbol,
                quantity = entity.quantity,
                ltp = entity.ltp,
                avgPrice = entity.avgPrice,
                close = entity.close
            )
        }
    }.flowOn(dispatcher)


    suspend fun saveHoldings(entities: List<UserHoldingEntity>) {
        withContext(dispatcher) {
            dao.clearHoldings()
            dao.insertHoldings(entities)
        }
    }
}
