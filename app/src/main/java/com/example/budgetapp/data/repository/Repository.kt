package com.example.budgetapp.data.repository

import com.example.budgetapp.data.model.Account
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAccounts(): Flow<List<Account>>
    suspend fun insertAccount(account: Account)
}
