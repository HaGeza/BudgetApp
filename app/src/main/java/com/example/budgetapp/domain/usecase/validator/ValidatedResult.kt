package com.example.budgetapp.domain.usecase.validator

data class ValidatedResult<T>(
    val value: T? = null,
    val error: ValidationError? = null
)

abstract class ValidationError

class RequiredFieldError : ValidationError()

class EmptyFieldError : ValidationError()

class InvalidNumberError : ValidationError()

class InvalidCurrencyError : ValidationError()
