package com.example.budgetapp.presentation.validation.validator

import com.example.budgetapp.presentation.validation.ValidationResult

interface BaseValidator<T> {
    operator fun invoke(value: T): ValidationResult<T>
}