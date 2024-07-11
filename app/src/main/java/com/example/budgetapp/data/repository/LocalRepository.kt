package com.example.budgetapp.data.repository

import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.dao.BaseDao
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.domain.repository.Repository
import javax.inject.Inject

abstract class LocalRepository<T>(
    private val dao: BaseDao<T>
) : Repository<T> {
    override fun getAll() = dao.getAll()
    override fun getById(id: Int) = dao.get(id)
    override suspend fun insert(entry: T) = dao.insert(entry)
    override suspend fun update(entry: T) = dao.update(entry)
    override suspend fun delete(entry: T) = dao.delete(entry)
}

class LocalAccountsRepository @Inject constructor(
    dao: AccountDao
) : LocalRepository<Account>(dao), AccountsRepository
