package com.example.budgetapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.model.Account

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}