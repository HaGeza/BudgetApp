package com.example.budgetapp.domain.model

import androidx.test.filters.SmallTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.Currency

@SmallTest
class ConvertersTest {
    lateinit var converters: Converters

    @Before
    fun setUp() {
        converters = Converters()
    }

    @Test
    fun `fromBigDecimal returns correct string`() {
        var bigDecimal = BigDecimal("100.00")
        assert(converters.fromBigDecimal(bigDecimal) == "100.00")

        bigDecimal = BigDecimal("31.456")
        assert(converters.fromBigDecimal(bigDecimal) == "31.456")
    }

    @Test
    fun `toBigDecimal returns correct BigDecimal`() {
        assert(converters.toBigDecimal("130.002") == BigDecimal("130.002"))
        assert(converters.toBigDecimal("777.777") == BigDecimal("777.777"))
    }

    @Test
    fun `toBigDecimal throws error on incorrect String`() {
        assertThrows(NumberFormatException::class.java) {
            converters.toBigDecimal("130.002.002")
        }
        assertThrows(NumberFormatException::class.java) {
            converters.toBigDecimal("BigDecimal")
        }
    }

    @Test
    fun `fromCurrency returns currency code`() {
        assert(converters.fromCurrency(Currency.getInstance("USD")) == "USD")
        assert(converters.fromCurrency(Currency.getInstance("EUR")) == "EUR")
    }

    @Test
    fun `toCurrency returns correct Currency`() {
        assert(converters.fromCurrency(Currency.getInstance("RON")) == "RON")
        assert(converters.fromCurrency(Currency.getInstance("JPY")) == "JPY")
    }

    @Test
    fun `toCurrency throws error on incorrect String`() {
        assertThrows(IllegalArgumentException::class.java) {
            converters.toCurrency("RONALDO")
        }
        assertThrows(IllegalArgumentException::class.java) {
            converters.toCurrency("usd")
        }
    }
}
