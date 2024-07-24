package com.example.budgetapp.presentation.viewmodel.state

import com.example.budgetapp.domain.usecase.validator.ValidatedResult

/**
 * Form state for the account creation form in the Domain layer
 * @param nameResult Result of the account name validation
 * @param currencyResult Result of the account currency validation
 * @param balanceResult Result of the account balance validation
 */
data class AccountFormState(
    val nameResult: ValidatedResult<String> = ValidatedResult(null),
    val currencyResult: ValidatedResult<String> = ValidatedResult(null),
    val balanceResult: ValidatedResult<String> = ValidatedResult(null),
) {
    /**
     * Check if the form state is valid
     * @return `true` if the form state is valid, `false` otherwise
     */
    fun isValid(): Boolean {
        return nameResult.error == null && currencyResult.error == null && balanceResult.error == null
    }
}
