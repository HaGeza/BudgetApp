package com.example.budgetapp.presentation.validation.state

import com.example.budgetapp.presentation.validation.ValidationResult

data class AccountFormState(
    var nameResult: ValidationResult<String> = ValidationResult(""),
    var currencyResult: ValidationResult<String> = ValidationResult("USD"),
    var balanceResult: ValidationResult<Double> = ValidationResult(0.0),
)
