package com.example.budgetapp.presentation.validation.validator

import com.example.budgetapp.presentation.validation.ValidationResult
import java.util.Currency

class CurrencyValidator : BaseValidator<String> {
    operator override fun invoke(value: String): ValidationResult<String> {
        return try {
            ValidationResult(value = Currency.getInstance(value).toString())
        } catch (e: IllegalArgumentException) {
            ValidationResult(error = "Invalid currency")
        }
    }
}