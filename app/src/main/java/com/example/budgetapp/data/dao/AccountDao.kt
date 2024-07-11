package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.budgetapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {
    @Query("SELECT * FROM accounts ORDER BY name ASC")
    override fun getAll(): Flow<List<Account>>

    @Query("SELECT * FROM accounts WHERE id = :id")
    override fun get(id: Int): Flow<Account>
}