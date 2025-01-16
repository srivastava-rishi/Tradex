package com.rsstudio.tradex.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsstudio.tradex.domain.mapper.toUiData
import com.rsstudio.tradex.domain.usecase.UserHoldingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val userHoldingsUseCase: UserHoldingsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenUiState())
        private set

    init {
        fetchHoldings()
    }

    private fun fetchHoldings() {
        viewModelScope.launch {
            userHoldingsUseCase().catch {
                Log.d("lio88", "fetchHoldings  catch block:  ${it}")
            }.collect {
                if (it.isSuccessful) {
                    it.body()?.let { response ->
                        Log.d("lio88", "fetchHoldings if :  $response")
                        uiState = uiState.copy(
                            screenState = ScreenState.NONE,
                            userHoldingData = response.toUiData()
                        )
                    }
                } else{
                    Log.d("lio88", "fetchHoldings else:  ${it.body()}")
                }
            }
        }
    }
}

data class HomeScreenUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val userHoldingData: List<UserHoldingsData> = emptyList()
) {
    data class UserHoldingsData(
        val symbol: String,
        val quantity: Int,
        val ltp: Double,
        val avgPrice: Double,
        val close: Double
    )
}

enum class ScreenState {
    NONE,
    LOADING,
    ERROR
}