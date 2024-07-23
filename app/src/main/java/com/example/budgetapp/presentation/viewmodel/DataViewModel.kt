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

/**
 * ViewModel for data operations. I provides CRUD operations through a repository, mapping between
 * presentation and domain entities.
 * @param <D> - Domain model type
 * @param <P> - Presentation model type
 * @param repository - Repository to use for data operations
 */
abstract class DataViewModel<D, P>(private val repository: Repository<D>) : ViewModel() {
    /**
     * Maps a presentation entity to a domain entity
     * @param presentation - Presentation entity to map
     * @return Domain entity
     */
    abstract fun presentationToDomain(presentation: P): D

    /**
     * Maps a domain entity to a presentation entity
     * @param domain - Domain entity to map
     * @return Presentation entity
     */
    abstract fun domainToPresentation(domain: D): P

    /**
     * Gets all entities
     * @return [Flow] of all entities
     */
    fun getAll(): Flow<List<P>> {
        return repository.getAll().map { list -> list.map(::domainToPresentation) }
    }

    /**
     * Gets an entity by its ID
     * @param id - ID of the entity to get
     * @return [Flow] of the entity with the given ID
     */
    fun getById(id: Int): Flow<P?> {
        return repository.getById(id).map({ it?.let(::domainToPresentation) })
    }

    /**
     * Inserts a new entity
     * @param entry - Entity to insert
     */
    fun insert(entry: P) {
        viewModelScope.launch {
            repository.insert(presentationToDomain(entry))
        }
    }

    /**
     * Updates an entity
     * @param entry - Entity to update
     */
    fun update(entry: P) {
        viewModelScope.launch {
            repository.update(presentationToDomain(entry))
        }
    }

    /**
     * Deletes an entity
     * @param entry - Entity to delete
     */
    fun delete(entry: P) {
        viewModelScope.launch {
            repository.delete(presentationToDomain(entry))
        }
    }
}

/**
 * ViewModel for CRUD operation on accounts, mapping between [AccountUI] and [Account]
 * @param repository - Repository to use for data operations
 */
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
