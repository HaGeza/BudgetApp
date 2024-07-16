package com.example.budgetapp.presentation.viewmodel.state

import com.example.budgetapp.domain.usecase.validator.ValidatedResult

data class AccountFormState(
    val nameResult: ValidatedResult<String> = ValidatedResult(null),
    val currencyResult: ValidatedResult<String> = ValidatedResult(null),
    val balanceResult: ValidatedResult<String> = ValidatedResult(null),
) {
    fun isValid(): Boolean {
        return nameResult.error == null && currencyResult.error == null && balanceResult.error == null
    }
}
