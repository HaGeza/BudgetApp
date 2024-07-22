package com.example.budgetapp.presentation.viewmodel.state

import com.example.budgetapp.domain.usecase.validator.InvalidCurrencyError
import com.example.budgetapp.domain.usecase.validator.InvalidNumberError
import com.example.budgetapp.domain.usecase.validator.RequiredFieldError
import com.example.budgetapp.domain.usecase.validator.ValidatedResult

/**
 * Form state for the account creation form in the Presentation layer
 * @param nameResult - Result of the account name validation
 * @param currencyResult - Result of the account currency validation
 * @param balanceResult - Result of the account balance validation
 */
data class AccountFormUIState(
    val name: String = "",
    val currency: String = "",
    val balance: String = "",
    val nameError: String? = null,
    val currencyError: String? = null,
    val balanceError: String? = null,
) {
    /**
     * Convert the form state to a Domain layer form state
     * @return The Domain layer form state
     */
    fun toFormState(): AccountFormState {
        return AccountFormState(
            nameResult = ValidatedResult(name),
            currencyResult = ValidatedResult(currency),
            balanceResult = ValidatedResult(balance),
        )
    }

    /**
     * Convert a Domain layer form state to a Presentation layer form state
     * @param state - The Domain layer form state
     * @return The Presentation layer form state
     */
    fun fromFormState(state: AccountFormState): AccountFormUIState {
        // TODO: use string resources for error messages
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
