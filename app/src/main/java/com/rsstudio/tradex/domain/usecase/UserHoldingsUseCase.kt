package com.rsstudio.tradex.domain.usecase

import com.rsstudio.tradex.data.repositories.UserHoldingsRepository
import javax.inject.Inject

class UserHoldingsUseCase @Inject constructor(
    private val repository: UserHoldingsRepository
) {
    suspend operator fun invoke() = repository.fetchUserHoldings()
}