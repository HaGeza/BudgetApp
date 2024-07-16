package com.example.budgetapp.domain.usecase.validator

open class StringValidator(private val required: Boolean = false) :
    BaseValidator<String> {
    operator override fun invoke(value: String?): ValidatedResult<String> {
        return if (required && (value == null || value.isEmpty())) {
            ValidatedResult(error = RequiredFieldError())
        } else {
            ValidatedResult(value = value)
        }
    }
}
