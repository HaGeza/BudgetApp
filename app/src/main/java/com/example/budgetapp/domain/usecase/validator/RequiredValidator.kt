package com.example.budgetapp.domain.usecase.validator

open class RequiredValidator<T>(private val required: Boolean = false) :
    BaseValidator<T> {
    operator override fun invoke(value: T?): ValidatedResult<T> {
        return if (!required || value != null) {
            ValidatedResult(value = value)
        } else {
            ValidatedResult(error = RequiredFieldError())
        }
    }
}
