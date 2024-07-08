package com.example.budgetapp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.model.Account
import com.example.budgetapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: Repository
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