package com.rsstudio.tradex.domain.mapper

import com.rsstudio.tradex.data.models.response.UserHoldingResponse
import com.rsstudio.tradex.presentation.home.HomeScreenUiState
import com.rsstudio.tradex.presentation.home.UserHoldingsData

fun UserHoldingResponse.toUiData() = data.userHolding.map { holding ->
    UserHoldingsData(
        symbol = holding.symbol,
        quantity = holding.quantity,
        ltp = holding.ltp,
        avgPrice = holding.avgPrice,
        close = holding.close
    )
}
