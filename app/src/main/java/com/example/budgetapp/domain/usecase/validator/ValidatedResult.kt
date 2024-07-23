package com.example.budgetapp.domain.usecase.validator

/**
 * Represents the result of a validation
 * @param <T> - the type of the value being validated
 * @param value - the value being validated, `null` by default
 * @param error - the error that occurred during validation, `null` by default
 */
data class ValidatedResult<T>(
    val value: T? = null,
    val error: ValidationError? = null
)

/** Abstract base class for validation errors */
abstract class ValidationError

/** Represents a required field validation error */
class RequiredFieldError : ValidationError()

/** Represents an invalid number validation error */
class InvalidNumberError : ValidationError()

/** Represents an invalid currency validation error */
class InvalidCurrencyError : ValidationError()
