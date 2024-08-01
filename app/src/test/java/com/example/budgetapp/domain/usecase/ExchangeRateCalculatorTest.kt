package com.example.budgetapp.domain.usecase

import androidx.test.filters.SmallTest
import com.example.budgetapp.assertExchangeRatesEqual
import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.domain.model.ExchangeRate
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.Currency

@SmallTest
class ExchangeRateCalculatorTest {
    private val exchangeRateDao = mockk<ExchangeRateDao>()
    lateinit var calculateExchangeRate: ExchangeRateCalculator

    private val usd = Currency.getInstance("USD")
    private val eur = Currency.getInstance("EUR")
    private val jpy = Currency.getInstance("JPY")
    private val xar = Currency.getInstance("XSU")

    @Before
    fun setUp() {
        calculateExchangeRate = ExchangeRateCalculator(exchangeRateDao);

        every { exchangeRateDao.getBySource("USD") } returns flowOf(ExchangeRate(usd, usd, 1.0))
        every { exchangeRateDao.getBySource("EUR") } returns flowOf(ExchangeRate(eur, usd, 1.08))
        every { exchangeRateDao.getBySource("JPY") } returns flowOf(ExchangeRate(jpy, usd, 0.0067))
        every { exchangeRateDao.getBySource("XAR") } returns flowOf(null)
    }

    @Test
    fun `rate is calculated correclty for existing currencies`() = runTest {
        val usdToUsd = calculateExchangeRate(usd, usd)
        assertExchangeRatesEqual(ExchangeRate(usd, usd, 1.0), usdToUsd)

        val eurToEur = calculateExchangeRate(eur, eur)
        assertExchangeRatesEqual(ExchangeRate(eur, eur, 1.0), eurToEur)

        val jpyToJpy = calculateExchangeRate(jpy, jpy)
        assertExchangeRatesEqual(ExchangeRate(jpy, jpy, 1.0), jpyToJpy)

        val eurToUsd = calculateExchangeRate(eur, usd)
        assertExchangeRatesEqual(ExchangeRate(eur, usd, 1.08), eurToUsd)

        val usdToEur = calculateExchangeRate(usd, eur)
        assertExchangeRatesEqual(ExchangeRate(usd, eur, 1.0 / 1.08), usdToEur)

        val jpyToUsd = calculateExchangeRate(jpy, usd)
        assertExchangeRatesEqual(ExchangeRate(jpy, usd, 0.0067), jpyToUsd)

        val usdToJpy = calculateExchangeRate(usd, jpy)
        assertExchangeRatesEqual(ExchangeRate(usd, jpy, 1.0 / 0.0067), usdToJpy)

        val eurToJpy = calculateExchangeRate(eur, jpy)
        assertExchangeRatesEqual(ExchangeRate(eur, jpy, 1.08 / 0.0067), eurToJpy)

        val jpyToEur = calculateExchangeRate(jpy, eur)
        assertExchangeRatesEqual(ExchangeRate(jpy, eur, 0.0067 / 1.08), jpyToEur)
    }

    @Test
    fun `rate calculation throws error for nonexistent currencies`() = runTest {
        var result = runCatching { calculateExchangeRate(xar, usd) }
        assert(result.isFailure)

        result = runCatching { calculateExchangeRate(usd, xar) }
        assert(result.isFailure)

        result = runCatching { calculateExchangeRate(xar, eur) }
        assert(result.isFailure)

        result = runCatching { calculateExchangeRate(eur, xar) }
        assert(result.isFailure)
    }
}
