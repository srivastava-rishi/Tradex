package com.rsstudio.tradex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsstudio.tradex.data.local.entity.UserHoldingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserHoldingDao {
    @Query("SELECT * FROM user_holdings")
    fun getAllHoldings(): Flow<List<UserHoldingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoldings(holdings: List<UserHoldingEntity>)

    @Query("DELETE FROM user_holdings")
    suspend fun clearHoldings()
}