package com.example.budgetapp.presentation.viewmodel.dataVM

import androidx.test.filters.SmallTest
import com.example.budgetapp.MainDispatcherRule
import com.example.budgetapp.assertExchangeRatesEqual
import com.example.budgetapp.domain.model.ExchangeRate
import com.example.budgetapp.domain.repository.ExchangeRatesRepository
import com.example.budgetapp.domain.usecase.ExchangeRateCalculator
import com.example.budgetapp.presentation.viewmodel.uimodel.ExchangeRateUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Currency

@SmallTest
class ExchangeRateViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var exchangeRateViewModel: ExchangeRateViewModel
    private lateinit var presentationExchangeRate: ExchangeRateUI
    private lateinit var domainExchangeRate: ExchangeRate
    private val mockRepository = mockk<ExchangeRatesRepository>()
    private val exchangeRateCalculator = mockk<ExchangeRateCalculator>()

    private val usd = Currency.getInstance("USD")
    private val eur = Currency.getInstance("EUR")

    @Before
    fun setUp() {
        exchangeRateViewModel = ExchangeRateViewModel(mockRepository, exchangeRateCalculator)
        presentationExchangeRate = ExchangeRateUI(
            source = "USD",
            other = "EUR",
            rate = "1.08",
            id = 1,
        )
        domainExchangeRate = ExchangeRate(
            source = usd,
            other = eur,
            rate = 1.08,
            id = 1,
        )
    }

    @Test
    fun `calculateExchangeRate calculates correct rate`() = runTest {
        coEvery { exchangeRateCalculator(usd, eur) } returns domainExchangeRate
        coEvery { exchangeRateCalculator(eur, usd) } returns ExchangeRate(eur, usd, 1 / 1.08)

        val usdToEur = exchangeRateViewModel.calculateExchangeRate(usd, eur)
        assertExchangeRatesEqual(domainExchangeRate, usdToEur)

        val eurToUsd = exchangeRateViewModel.calculateExchangeRate(eur, usd)
        assertExchangeRatesEqual(ExchangeRate(eur, usd, 1 / 1.08), eurToUsd)
    }

    @Test
    fun `presentationToDomain converts presentation model to domain model`() {
        val result = exchangeRateViewModel.presentationToDomain(presentationExchangeRate)
        assertEquals(domainExchangeRate, result)
    }

    @Test
    fun `presentationToDomain throws NumberFormatException if rate is not correctly formatted`() {
        val invalidRate = presentationExchangeRate.copy(rate = "invalid")
        assertThrows(NumberFormatException::class.java) {
            exchangeRateViewModel.presentationToDomain(invalidRate)
        }
    }

    @Test
    fun `presentationToDomain throws IllegalArgumentException if source or other does not exist`() {
        val invalidSource = presentationExchangeRate.copy(source = "INVALID")
        assertThrows(IllegalArgumentException::class.java) {
            exchangeRateViewModel.presentationToDomain(invalidSource)
        }

        val invalidOther = presentationExchangeRate.copy(other = "NOT REAL")
        assertThrows(IllegalArgumentException::class.java) {
            exchangeRateViewModel.presentationToDomain(invalidOther)
        }
    }

    @Test
    fun `domainToPresentation converts domain model to presentation model`() {
        val result = exchangeRateViewModel.domainToPresentation(domainExchangeRate)
        assertEquals(presentationExchangeRate, result)
    }
}
