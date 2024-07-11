package com.example.budgetapp.presentation.validation

data class ValidationResult<T>(
    var value: T? = null,
    var error: String? = null
)