package com.example.budgetapp.presentation.validation.validator

import com.example.budgetapp.presentation.validation.ValidationResult

class NumberValidator : BaseValidator<String> {
    operator override fun invoke(value: String): ValidationResult<String> {
        return try {
            ValidationResult(value.toDouble().toString())
        } catch (e: NumberFormatException) {
            ValidationResult(error = "Invalid number")
        }
    }
}