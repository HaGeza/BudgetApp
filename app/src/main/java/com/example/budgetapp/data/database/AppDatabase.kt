package com.example.budgetapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.model.ExchangeRate

/** Room database for the application. */
@Database(entities = [Account::class, ExchangeRate::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /** Get the Data Access Object for the accounts table. */
    abstract fun accountDao(): AccountDao

    /** Get the Data Access Object for the exchange rates table. */
    abstract fun exchangeRateDao(): ExchangeRateDao
}
