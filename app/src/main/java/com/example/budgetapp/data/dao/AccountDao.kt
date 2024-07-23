package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.budgetapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

/** Data Access Object for the accounts table. */
@Dao
interface AccountDao : BaseDao<Account> {
    /** Get all accounts ordered by name. */
    @Query("SELECT * FROM accounts ORDER BY name ASC")
    override fun getAll(): Flow<List<Account>>

    /**
     * Get an account by id.
     * @param id The id of the account to get.
     * */
    @Query("SELECT * FROM accounts WHERE id = :id")
    override fun getById(id: Int): Flow<Account?>
}
