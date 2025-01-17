package com.rsstudio.tradex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_holdings")
data class UserHoldingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)