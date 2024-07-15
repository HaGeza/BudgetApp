package com.example.budgetapp.domain.usecase.validator

class NonEmptyStringValidator(private val required: Boolean) : RequiredValidator<String>(required) {
    operator override fun invoke(value: String?): ValidatedResult<String> {
        val result = super.invoke(value)
        if (result.error != null) {
            return result
        }

        return if (!(value!!.isEmpty())) {
            ValidatedResult(value = value)
        } else {
            ValidatedResult<String>(error = EmptyFieldError())
        }
    }
}
