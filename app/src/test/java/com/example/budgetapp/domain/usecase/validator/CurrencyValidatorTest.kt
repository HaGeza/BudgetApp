package com.example.budgetapp.domain.usecase.validator

import androidx.test.filters.SmallTest
import org.junit.Before
import org.junit.Test

@SmallTest
class CurrencyValidatorTest {
    lateinit var currencyValidator: CurrencyValidator

    @Before
    fun setUp() {
        currencyValidator = CurrencyValidator()
    }

    @Test
    fun `when input is incorrect format return invalid currency error`() {
        assert(currencyValidator("a").error is InvalidCurrencyError)
        assert(currencyValidator("ab").error is InvalidCurrencyError)
        assert(currencyValidator("usd").error is InvalidCurrencyError)
        assert(currencyValidator("LEL").error is InvalidCurrencyError)
        assert(currencyValidator("EURO").error is InvalidCurrencyError)
        assert(currencyValidator("89fda").error is InvalidCurrencyError)
        assert(currencyValidator(":----------)").error is InvalidCurrencyError)
    }

    @Test
    fun `when input is valid return value`() {
        var result = currencyValidator("USD")
        assert(result.value == "USD" && result.error == null)

        result = currencyValidator("EUR")
        assert(result.value == "EUR" && result.error == null)

        result = currencyValidator("JPY")
        assert(result.value == "JPY" && result.error == null)
    }
}
