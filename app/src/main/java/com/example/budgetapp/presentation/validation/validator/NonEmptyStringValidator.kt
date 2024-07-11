package com.example.budgetapp.presentation.validation.validator

import com.example.budgetapp.presentation.validation.ValidationResult

class NonEmptyStringValidator : BaseValidator<String> {
    operator override fun invoke(value: String): ValidationResult<String> {
        return if (!value.isEmpty()) {
            ValidationResult(value = value)
        } else {
            ValidationResult(error = "Field cannot be empty")
        }
    }
}