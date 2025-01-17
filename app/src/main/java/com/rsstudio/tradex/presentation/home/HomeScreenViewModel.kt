package com.rsstudio.tradex.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsstudio.tradex.domain.usecase.CalculatePortfolioMetricsUseCase
import com.rsstudio.tradex.domain.usecase.UserHoldingsUseCase
import com.rsstudio.tradex.util.formatAsCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val userHoldingsUseCase: UserHoldingsUseCase,
    private val calculatePortfolioMetricsUseCase: CalculatePortfolioMetricsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenUiState())
        private set

    init {
        fetchHoldings()
    }

    private fun fetchHoldings() {
        viewModelScope.launch {
            userHoldingsUseCase().catch {
                Log.d("lion1223", "fetchHoldings: $it")
                uiState = uiState.copy(screenState = ScreenState.ERROR)
            }.collect { userHoldings ->
                updateUiStateWithMetrics(userHoldings)
            }
        }
    }

    private fun updateUiStateWithMetrics(userHoldings: List<UserHoldingsData>) {
        val userData = userHoldings.map { userHoldingData ->
            userHoldingData.copy(
                totalProfitAndLoss = calculatePortfolioMetricsUseCase.getTotalProfitAndLoss(
                    userHoldingData.ltp * userHoldingData.quantity,
                    userHoldingData.avgPrice * userHoldingData.quantity
                )
            )
        }
        val currentValue = calculatePortfolioMetricsUseCase.getCurrentSum(
            userData.map { data -> Pair(data.ltp, data.quantity) }
        )
        val totalInvestment = calculatePortfolioMetricsUseCase.getTotalInvestment(
            userData.map { data -> Pair(data.avgPrice, data.quantity) }
        )

        uiState = uiState.copy(
            screenState = ScreenState.NONE,
            userHoldingData = userData,
            sheetData = SheetData(
                currentValue = currentValue.formatAsCurrency(),
                totalInvestment = totalInvestment.formatAsCurrency(),
                todayProfitAndLoss = calculatePortfolioMetricsUseCase.getTodayProfitAndLoss(
                    userData.map { data -> Triple(data.close, data.ltp, data.quantity) }
                ).formatAsCurrency(),
                totalProfitAndLoss = (currentValue - totalInvestment).formatAsCurrency()
            )
        )
    }
}

data class HomeScreenUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val userHoldingData: List<UserHoldingsData> = emptyList(),
    val sheetData: SheetData = SheetData()
)

data class UserHoldingsData(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double,
    val totalProfitAndLoss: String = ""
)

data class SheetData(
    val currentValue: String = "",
    val totalInvestment: String = "",
    val todayProfitAndLoss: String = "",
    val totalProfitAndLoss: String = ""
)

enum class ScreenState {
    NONE,
    LOADING,
    ERROR
}