package com.example.budgetapp.domain.usecase

import com.example.budgetapp.Constants.Companion.BASE_CURRENCY_CODE
import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.domain.model.ExchangeRate
import kotlinx.coroutines.flow.first
import java.util.Currency
import javax.inject.Inject

/**
 * Use case to calculate the exchange rate between two currencies
 * @param exchangeRateDao Data access object to retrieve exchange rates from `source` to currency with [BASE_CURRENCY_CODE]
 */
class ExchangeRateCalculator @Inject constructor(private val exchangeRateDao: ExchangeRateDao) {
    val baseCurrency = Currency.getInstance(BASE_CURRENCY_CODE)

    /**
     * Calculate the exchange rate between two currencies
     * @param exchangeRate Describes the source and other currencies that the rate should be calculated for
     * @return The exchange rate between the two currencies
     */
    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(source: Currency, other: Currency): ExchangeRate {
        if (source == other) {
            return ExchangeRate(source, other, 1.0)
        }

        val nonBase = if (source != baseCurrency) source else other
        val actualExchangeRate =
            exchangeRateDao.getBySource(nonBase.toString()).first()
                ?: throw IllegalArgumentException("Exchange rate not found")

        if (other == baseCurrency) {
            return actualExchangeRate
        }
        if (source == baseCurrency) {
            return ExchangeRate(source, other, 1.0 / actualExchangeRate.rate)
        }
        val otherExchangeRate =
            exchangeRateDao.getBySource(other.toString()).first()
                ?: throw IllegalArgumentException("Exchange rate not found")

        return ExchangeRate(source, other, actualExchangeRate.rate / otherExchangeRate.rate)
    }
}
