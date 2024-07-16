package com.example.budgetapp.presentation.viewmodel.state

import com.example.budgetapp.domain.usecase.validator.InvalidCurrencyError
import com.example.budgetapp.domain.usecase.validator.InvalidNumberError
import com.example.budgetapp.domain.usecase.validator.RequiredFieldError
import com.example.budgetapp.domain.usecase.validator.ValidatedResult

data class AccountFormUIState(
    val name: String = "",
    val currency: String = "",
    val balance: String = "",
    val nameError: String? = null,
    val currencyError: String? = null,
    val balanceError: String? = null,
) {
    fun toFormState(): AccountFormState {
        return AccountFormState(
            nameResult = ValidatedResult(name),
            currencyResult = ValidatedResult(currency),
            balanceResult = ValidatedResult(balance),
        )
    }

    fun fromFormState(state: AccountFormState): AccountFormUIState {
        return AccountFormUIState(
            name = state.nameResult.value ?: "",
            currency = state.currencyResult.value ?: "",
            balance = state.balanceResult.value ?: "",

            nameError = when (state.nameResult.error) {
                is RequiredFieldError -> "Name is required"
                null -> null
                else -> "Error"
            },

            currencyError = when (state.currencyResult.error) {
                is RequiredFieldError -> "Currency is required"
                is InvalidCurrencyError -> "Invalid currency"
                null -> null
                else -> "Error"
            },

            balanceError = when (state.balanceResult.error) {
                is RequiredFieldError -> "Balance is required"
                is InvalidNumberError -> "Invalid number"
                null -> null
                else -> "Error"
            }
        )
    }
}
