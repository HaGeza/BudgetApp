package com.example.budgetapp.domain.usecase.validator

interface BaseValidator<T> {
    operator fun invoke(value: T?): ValidatedResult<T>
}
