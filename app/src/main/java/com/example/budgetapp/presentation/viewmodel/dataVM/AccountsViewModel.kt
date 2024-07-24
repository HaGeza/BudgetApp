package com.example.budgetapp.presentation.viewmodel.dataVM

import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Currency
import javax.inject.Inject

/**
 * ViewModel for CRUD operation on accounts, mapping between [AccountUI] and [Account]
 * @param repository - Repository to use for data operations
 */
@HiltViewModel
class AccountsViewModel @Inject constructor(
    repository: AccountsRepository
) : DataViewModel<Account, AccountUI>(repository) {
    override fun presentationToDomain(presentation: AccountUI): Account {
        return Account(
            name = presentation.name,
            balance = presentation.balance.toBigDecimal(),
            currency = Currency.getInstance(presentation.currency)
        )
    }

    override fun domainToPresentation(domain: Account): AccountUI {
        return AccountUI(
            id = domain.id,
            name = domain.name,
            balance = domain.balance.toString(),
            currency = domain.currency.currencyCode
        )
    }
}
