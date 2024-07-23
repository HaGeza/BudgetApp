package com.example.budgetapp.domain.usecase.validator

/**
 * Interface for a generic validator
 * @param <T> - the type of entity the validator handles
 */
interface BaseValidator<T> {
    /**
     * Validates the given value
     * @param value - the value to validate
     * @return a [ValidatedResult] representing the validation result
     */
    operator fun invoke(value: T?): ValidatedResult<T>
}
