package com.example.budgetapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.domain.repository.Repository
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Currency
import javax.inject.Inject

abstract class DataViewModel<D, P>(private val repository: Repository<D>) : ViewModel() {
    abstract fun presentationToDomain(presentation: P): D

    abstract fun domainToPresentation(domain: D): P

    fun getAll(): Flow<List<P>> {
        return repository.getAll().map { list -> list.map(::domainToPresentation) }
    }

    fun getById(id: Int): Flow<P> {
        return repository.getById(id).map(::domainToPresentation)
    }

    fun insert(entry: P) {
        viewModelScope.launch {
            repository.insert(presentationToDomain(entry))
        }
    }

    fun update(entry: P) {
        viewModelScope.launch {
            repository.update(presentationToDomain(entry))
        }
    }

    fun delete(entry: P) {
        viewModelScope.launch {
            repository.delete(presentationToDomain(entry))
        }
    }
}

@HiltViewModel
class AccountsViewModel @Inject constructor(
    repository: AccountsRepository
) : DataViewModel<Account, AccountUI>(repository) {
    override fun presentationToDomain(presentation: AccountUI): Account {
        return Account(
            name = presentation.name,
            balance = presentation.balance.toBigDecimal(),
            currency = Currency.getInstance(presentation.currency)
        )
    }

    override fun domainToPresentation(domain: Account): AccountUI {
        return AccountUI(
            id = domain.id,
            name = domain.name,
            balance = domain.balance.toString(),
            currency = domain.currency.currencyCode
        )
    }
}
