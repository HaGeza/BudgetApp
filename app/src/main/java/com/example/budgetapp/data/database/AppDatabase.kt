package com.example.budgetapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.domain.model.Account

/** Room database for the application. */
@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /** Get the Data Access Object for the accounts table. */
    abstract fun accountDao(): AccountDao
}
