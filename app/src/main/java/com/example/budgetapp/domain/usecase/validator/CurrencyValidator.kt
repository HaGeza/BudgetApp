package com.example.budgetapp.domain.usecase.validator

import java.util.Currency

/**
 * A validator for currency codes
 * @param required - whether the value is required, `true` by default
 */
class CurrencyValidator(private val required: Boolean = true) : StringValidator(required) {
    /**
     * Validates the given value
     * @param value - the value to validate
     * @return a [ValidatedResult] representing the validation result. If the value is not a valid
     * currency code, `error` is set to [InvalidCurrencyError].
     */
    operator override fun invoke(value: String?): ValidatedResult<String> {
        val result = super.invoke(value)
        if (result.error != null) {
            return result
        }

        return try {
            Currency.getInstance(value)
            ValidatedResult(value = value)
        } catch (e: IllegalArgumentException) {
            ValidatedResult(error = InvalidCurrencyError())
        }
    }
}
