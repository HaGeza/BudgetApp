package com.example.budgetapp.domain.usecase.validator

/**
 * A validator for strings
 * @param required - whether the value is required, `false` by default
 */
open class StringValidator(private val required: Boolean = false) :
    BaseValidator<String> {
    /**
     * Validates the given value
     * @param value - the value to validate
     * @return a [ValidatedResult] representing the validation result. If the value is required and
     * empty or `null`, `error` is set to [RequiredFieldError].
     */
    operator override fun invoke(value: String?): ValidatedResult<String> {
        return if (required && (value == null || value.isEmpty())) {
            ValidatedResult(error = RequiredFieldError())
        } else {
            ValidatedResult(value = value)
        }
    }
}
