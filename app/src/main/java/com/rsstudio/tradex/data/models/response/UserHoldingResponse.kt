package com.rsstudio.tradex.data.models.response

data class UserHoldingResponse(
    val data: Data
) {
    data class Data(
        val userHolding: List<UserHolding>
    )

    data class UserHolding(
        val symbol: String,
        val quantity: Int,
        val ltp: Double,
        val avgPrice: Double,
        val close: Double
    )
}
