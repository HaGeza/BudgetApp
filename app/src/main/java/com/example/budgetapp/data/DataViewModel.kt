package com.example.budgetapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    fun getAccounts(): Flow<List<Account>> {
        return repository.getAccounts()
    }

    fun insertAccount(account: Account) {
        viewModelScope.launch {
            repository.insertAccount(account)
        }
    }
}