package com.example.budgetapp.domain.usecase.validator

class NumberValidator(private val required: Boolean) : RequiredValidator<String>(required) {
    operator override fun invoke(value: String?): ValidatedResult<String> {
        val result = super.invoke(value)
        if (result.error != null) {
            return result
        }

        return try {
            ValidatedResult(value?.toDouble().toString())
        } catch (e: NumberFormatException) {
            ValidatedResult(error = InvalidNumberError())
        }
    }
}
