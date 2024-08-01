package com.example.budgetapp.presentation.viewmodel.dataVM

import androidx.lifecycle.viewModelScope
import com.example.budgetapp.domain.model.ExchangeRate
import com.example.budgetapp.domain.repository.ExchangeRatesRepository
import com.example.budgetapp.domain.usecase.ExchangeRateCalculator
import com.example.budgetapp.presentation.viewmodel.uimodel.ExchangeRateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Currency
import javax.inject.Inject

/**
 * ViewModel for CRUD operation on accounts, mapping between [ExchangeRateUI] and [ExchangeRate]
 * @param repository Repository to use for data operations
 */
@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    repository: ExchangeRatesRepository,
    private val exchangeRateCalculator: ExchangeRateCalculator,
) : DataViewModel<ExchangeRate, ExchangeRateUI>(repository) {
    /**
     * Calculates the exchange rate between two currencies
     * @param source Source currency
     * @param other Other currency
     * @return [ExchangeRate] with the rate between the two currencies
     */
    fun calculateExchangeRate(source: Currency, other: Currency): ExchangeRate {
        var result: ExchangeRate? = null
        viewModelScope.launch {
            result = exchangeRateCalculator(source, other)
        }
        return result!!
    }

    /**
     * Converts [ExchangeRateUI] to [ExchangeRate]. May throw [NumberFormatException] if `presentation.rate` is not correctly formatted, or [IllegalArgumentException] if either `presentation.source` or `presentation.final` are not valid currency codes.
     * @param presentation [ExchangeRateUI] to convert
     * @return [ExchangeRate] converted from [ExchangeRateUI]
     * */
    @Throws(NumberFormatException::class, IllegalArgumentException::class)
    override fun presentationToDomain(presentation: ExchangeRateUI): ExchangeRate {
        return ExchangeRate(
            source = Currency.getInstance(presentation.source),
            other = Currency.getInstance(presentation.other),
            rate = presentation.rate.toDouble(),
            id = presentation.id,
        )
    }

    /**
     * Converts [ExchangeRate] to [ExchangeRateUI].
     * @param domain [ExchangeRate] to convert
     * @return [ExchangeRateUI] converted from [ExchangeRate]
     * */
    override fun domainToPresentation(domain: ExchangeRate): ExchangeRateUI {
        return ExchangeRateUI(
            source = domain.source.currencyCode,
            other = domain.other.currencyCode,
            rate = domain.rate.toString(),
            id = domain.id,
        )
    }
}
