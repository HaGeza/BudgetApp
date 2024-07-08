package com.example.budgetapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts ORDER BY name ASC")
    fun getAccounts(): Flow<List<Account>>

    @Insert
    suspend fun insertAccount(account: Account)
}