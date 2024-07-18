package com.example.budgetapp.domain.usecase.validator

class NumberValidator(private val required: Boolean = true) : StringValidator(required) {
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
