package com.example.budgetapp.presentation.viewmodel.dataVM

import com.example.budgetapp.domain.model.ExchangeRate
import com.example.budgetapp.domain.repository.ExchangeRatesRepository
import com.example.budgetapp.presentation.viewmodel.uimodel.ExchangeRateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Currency
import javax.inject.Inject

/**
 * ViewModel for CRUD operation on accounts, mapping between [ExchangeRateUI] and [ExchangeRate]
 * @param repository Repository to use for data operations
 */
@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    repository: ExchangeRatesRepository
) : DataViewModel<ExchangeRate, ExchangeRateUI>(repository) {
    /**
     * Converts [ExchangeRateUI] to [ExchangeRate]. May throw [NumberFormatException] if `presentation.rate` is not correctly formatted, or [IllegalArgumentException] if either `presentation.source` or `presentation.final` are not valid currency codes.
     * @param presentation [ExchangeRateUI] to convert
     * @return [ExchangeRate] converted from [ExchangeRateUI]
     * */
    @Throws(NumberFormatException::class)
    override fun presentationToDomain(presentation: ExchangeRateUI): ExchangeRate {
        return ExchangeRate(
            source = Currency.getInstance(presentation.source),
            other = Currency.getInstance(presentation.other),
            rate = presentation.rate
        )
    }

    /**
     * Converts [ExchangeRate] to [ExchangeRateUI].
     * @param domain [ExchangeRate] to convert
     * @return [ExchangeRateUI] converted from [ExchangeRate]
     * */
    override fun domainToPresentation(domain: ExchangeRate): ExchangeRateUI {
        return ExchangeRateUI(
            id = domain.id,
            source = domain.source.currencyCode,
            other = domain.other.currencyCode,
            rate = domain.rate,
        )
    }
}
