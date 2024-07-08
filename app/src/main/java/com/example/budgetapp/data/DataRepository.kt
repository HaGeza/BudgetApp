package com.example.budgetapp.data

import javax.inject.Inject

class DataRepository @Inject constructor(private val accountDao: AccountDao) {
    fun getAccounts() = accountDao.getAccounts()
    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)
}