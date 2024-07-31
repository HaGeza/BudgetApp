package com.example.budgetapp.presentation.viewmodel.dataVM

import com.example.budgetapp.domain.constants.CurrencyDecimals
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.RoundingMode
import java.util.Currency
import javax.inject.Inject

/**
 * ViewModel for CRUD operation on accounts, mapping between [AccountUI] and [Account]
 * @param repository Repository to use for data operations
 */
@HiltViewModel
class AccountsViewModel @Inject constructor(
    repository: AccountsRepository
) : DataViewModel<Account, AccountUI>(repository) {
    /**
     * Converts [AccountUI] to [Account]. May throw [NumberFormatException] if `presentation.balance` is not correctly formatted or [IllegalArgumentException] if `presentation.currency` is not a valid currency code.
     * @param presentation [AccountUI] to convert
     * @return [Account] converted from [AccountUI]
     * */
    @Throws(NumberFormatException::class, IllegalArgumentException::class)
    override fun presentationToDomain(presentation: AccountUI): Account {
        return Account(
            name = presentation.name,
            balance = presentation.balance.toBigDecimal(),
            currency = Currency.getInstance(presentation.currency)
        )
    }

    /**
     * Converts [Account] to [AccountUI]. May throw [IllegalArgumentException] if `domain.currency.currencyCode` is not a supported currency code.
     * @param domain [Account] to convert
     * @return [AccountUI] converted from [Account]
     * */
    @Throws(IllegalArgumentException::class)
    override fun domainToPresentation(domain: Account): AccountUI {
        val currency = domain.currency.currencyCode
        val decimals = CurrencyDecimals.CURRENCY_DECIMALS.get(currency)
            ?: throw IllegalArgumentException("Currency not found: $currency")
        val balance = domain.balance.setScale(decimals.toInt(), RoundingMode.FLOOR).toString()

        return AccountUI(
            id = domain.id,
            name = domain.name,
            balance = balance,
            currency = currency,
        )
    }
}
