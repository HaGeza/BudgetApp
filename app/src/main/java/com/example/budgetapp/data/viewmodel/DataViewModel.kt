package com.example.budgetapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.model.Account
import com.example.budgetapp.data.repository.AccountsRepository
import com.example.budgetapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class DataViewModel<T>(private val repository: Repository<T>) : ViewModel() {
    fun getAll(): Flow<List<T>> {
        return repository.getAll()
    }

    fun getById(id: Int): Flow<T> {
        return repository.getById(id)
    }

    fun insert(entry: T) {
        viewModelScope.launch {
            repository.insert(entry)
        }
    }

    fun delete(entry: T) {
        viewModelScope.launch {
            repository.delete(entry)
        }
    }
}

@HiltViewModel
class AccountsViewModel @Inject constructor(
    repository: AccountsRepository
) : DataViewModel<Account>(repository)
