package com.rsstudio.tradex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rsstudio.tradex.data.local.dao.UserHoldingDao
import com.rsstudio.tradex.data.local.entity.UserHoldingEntity

@Database(entities = [UserHoldingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userHoldingsDao(): UserHoldingDao
}