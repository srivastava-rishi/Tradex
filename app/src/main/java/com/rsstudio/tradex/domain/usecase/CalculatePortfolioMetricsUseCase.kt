package com.rsstudio.tradex.domain.usecase

import com.rsstudio.tradex.util.formatAsCurrency
import javax.inject.Inject

class CalculatePortfolioMetricsUseCase @Inject constructor() {

    fun getCurrentSum(tradeDetails: List<Pair<Double, Int>>) =
        tradeDetails.sumOf { (lastTradedPrice, quantityOwned) ->
            lastTradedPrice * quantityOwned
        }

    fun getTotalInvestment(investmentDetails: List<Pair<Double, Int>>) =
        investmentDetails.sumOf { (averagePrice, quantityOwned) ->
            averagePrice * quantityOwned
        }

    fun getTodayProfitAndLoss(tradeDetails: List<Triple<Double, Double, Int>>) =
        tradeDetails.sumOf { (close, lastTradedPrice, quantityOwned) ->
            (close - lastTradedPrice) * quantityOwned
        }

    fun getTotalProfitAndLoss(currentValue: Double, totalInvestedAmount: Double) =
        (currentValue - totalInvestedAmount).formatAsCurrency()
}