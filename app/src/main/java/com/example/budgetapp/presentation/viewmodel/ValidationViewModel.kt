package com.example.budgetapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.budgetapp.presentation.validation.state.AccountFormState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ValidationViewModel : ViewModel() {
    var accountFormState by mutableStateOf(AccountFormState())
}