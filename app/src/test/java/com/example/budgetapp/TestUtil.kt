package com.example.budgetapp

import com.example.budgetapp.domain.model.ExchangeRate
import org.junit.Assert.assertEquals

fun assertExchangeRatesEqual(
    first: ExchangeRate,
    second: ExchangeRate,
    delta: Double = 1e-5
) {
    assertEquals(first.source, second.source)
    assertEquals(first.other, second.other)
    assertEquals(first.rate, second.rate, delta)
}
