package com.example.budgetapp.presentation.viewmodel.state

import android.content.Context
import com.example.budgetapp.R.string.input_internal_error
import com.example.budgetapp.R.string.input_invalid
import com.example.budgetapp.R.string.input_number_invalid
import com.example.budgetapp.R.string.input_required
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
     * @param context - The Android context
     * @return The Presentation layer form state
     */
    fun fromFormState(state: AccountFormState, context: Context): AccountFormUIState {
        return AccountFormUIState(
            name = state.nameResult.value ?: "",
            currency = state.currencyResult.value ?: "",
            balance = state.balanceResult.value ?: "",

            nameError = when (state.nameResult.error) {
                is RequiredFieldError -> context.getString(input_required)
                null -> null
                else -> context.getString(input_internal_error)
            },

            currencyError = when (state.currencyResult.error) {
                is RequiredFieldError -> context.getString(input_required)
                is InvalidCurrencyError -> context.getString(input_invalid)
                null -> null
                else -> context.getString(input_internal_error)
            },

            balanceError = when (state.balanceResult.error) {
                is RequiredFieldError -> context.getString(input_required)
                is InvalidNumberError -> context.getString(input_number_invalid)
                null -> null
                else -> context.getString(input_internal_error)
            }
        )
    }
}
