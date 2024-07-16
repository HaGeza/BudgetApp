package com.example.budgetapp.domain.usecase.validator

import java.util.Currency

class CurrencyValidator(private val required: Boolean) : RequiredValidator<String>(required) {
    operator override fun invoke(value: String?): ValidatedResult<String> {
        val result = super.invoke(value)
        if (result.error != null) {
            return result
        }

        return try {
            ValidatedResult(value = Currency.getInstance(value).toString())
        } catch (e: IllegalArgumentException) {
            ValidatedResult(error = InvalidCurrencyError())
        }
    }
}
