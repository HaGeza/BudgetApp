package com.example.budgetapp.domain.usecase.validator

import org.junit.Before
import org.junit.Test

class NumberValidatorTest {
    lateinit var numberValidator: NumberValidator

    @Before
    fun setUp() {
        numberValidator = NumberValidator()
    }

    @Test
    fun `when input is not a number then return invalid number error`() {
        assert(numberValidator("a").error is InvalidNumberError)
        assert(numberValidator("123x.9").error is InvalidNumberError)
        assert(numberValidator("0..3").error is InvalidNumberError)
        assert(numberValidator("1.2.3").error is InvalidNumberError)
    }

    @Test
    fun `when input is a number then return value`() {
        var result = numberValidator("0")
        assert(result.value == "0" && result.error == null)

        result = numberValidator("123")
        assert(result.value == "123" && result.error == null)

        result = numberValidator("123.9")
        assert(result.value == "123.9" && result.error == null)

        result = numberValidator("0.3112")
        assert(result.value == "0.3112" && result.error == null)
    }
}
