package com.example.budgetapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.NonEmptyStringValidator
import com.example.budgetapp.domain.usecase.validator.NumberValidator
import com.example.budgetapp.presentation.viewmodel.state.AccountFormUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountFormViewModel @Inject constructor(
    private val nameValidator: NonEmptyStringValidator,
    private val balanceValidator: NumberValidator,
    private val currencyValidator: CurrencyValidator,
) : ViewModel() {
    var formUIState by mutableStateOf(AccountFormUIState())

    fun onNameChanged(name: String) {
        formUIState = formUIState.copy(name = name)
    }

    fun onCurrencyChanged(currency: String) {
        formUIState = formUIState.copy(currency = currency)
    }

    fun onBalanceChanged(balance: String) {
        formUIState = formUIState.copy(balance = balance)
    }

    fun onSubmit(): Boolean {
        var formState = formUIState.toFormState()
        formState = formState.copy(
            nameResult = nameValidator(formState.nameResult.value),
            balanceResult = balanceValidator(formState.balanceResult.value),
            currencyResult = currencyValidator(formState.currencyResult.value),
        )
        formUIState = formUIState.fromFormState(formState)

        return formState.isValid()
    }
}
