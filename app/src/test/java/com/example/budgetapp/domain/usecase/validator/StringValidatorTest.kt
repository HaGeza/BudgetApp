package com.example.budgetapp.domain.usecase.validator

import androidx.test.filters.SmallTest
import org.junit.Before
import org.junit.Test

@SmallTest
class StringValidatorTest {
    lateinit var requiredValidator: StringValidator
    lateinit var nonRequiredValidator: StringValidator

    @Before
    fun setUp() {
        requiredValidator = StringValidator(true)
        nonRequiredValidator = StringValidator(false)
    }

    @Test
    fun `when value is null and required is true then return error`() {
        assert(requiredValidator(null).error is RequiredFieldError)
    }

    @Test
    fun `when value is empty and required is true then return erro`() {
        assert(requiredValidator("").error is RequiredFieldError)
    }

    @Test
    fun `when value is not null and not empty and required is true then return value`() {
        val result = requiredValidator("value")
        assert(result.value == "value" && result.error == null)
    }

    @Test
    fun `when required is false then return value`() {
        var result = nonRequiredValidator(null)
        assert(result.value == null && result.error == null)

        result = nonRequiredValidator("")
        assert(result.value == "" && result.error == null)

        result = nonRequiredValidator("value")
        assert(result.value == "value" && result.error == null)
    }
}
