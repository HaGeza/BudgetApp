package com.example.budgetapp.domain.usecase.validator

/**
 * A validator for strings that should represent numbers
 * @param required - whether the value is required, `true` by default
 */
class NumberValidator(private val required: Boolean = true) : StringValidator(required) {
    /**
     * Validates the given value
     * @param value - the value to validate
     * @return a [ValidatedResult] representing the validation result. If the value is not a valid
     * number, `error` is set to [InvalidNumberError].
     */
    operator override fun invoke(value: String?): ValidatedResult<String> {
        val result = super.invoke(value)
        if (result.error != null) {
            return result
        }

        return try {
            value?.toDouble()
            ValidatedResult(value = value)
        } catch (e: NumberFormatException) {
            ValidatedResult(error = InvalidNumberError())
        }
    }
}
