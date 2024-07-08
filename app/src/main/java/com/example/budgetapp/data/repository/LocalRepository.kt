package com.example.budgetapp.data.repository

import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.model.Account
import javax.inject.Inject

class LocalRepository @Inject constructor(private val accountDao: AccountDao) : Repository {
    override fun getAccounts() = accountDao.getAll()
    override suspend fun insertAccount(account: Account) = accountDao.insert(account)
}
