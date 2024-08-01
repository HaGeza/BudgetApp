package com.example.budgetapp.presentation.viewmodel.dataVM

import androidx.test.filters.SmallTest
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import io.mockk.mockk
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import java.util.Currency

@SmallTest
class AccountsViewModelTest {
    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var presentationAccount: AccountUI
    private lateinit var domainAccount: Account
    private val mockRepository = mockk<AccountsRepository>()

    @Before
    fun setUp() {
        accountsViewModel = AccountsViewModel(mockRepository)
        presentationAccount = AccountUI(
            name = "Account A",
            balance = "100.00",
            currency = "USD",
            id = 1,
        )
        domainAccount = Account(
            name = "Account B",
            balance = "230.50".toBigDecimal(),
            currency = Currency.getInstance("USD"),
            id = 1,
        )
    }

    @Test
    fun `presentationToDomain converts presentation model to domain model`() {
        val domainModel = accountsViewModel.presentationToDomain(presentationAccount)

        assert(domainModel.name == "Account A")
        assert(domainModel.balance == "100.00".toBigDecimal())
        assert(domainModel.currency.currencyCode == "USD")
    }

    @Test
    fun `presentationToDomain throws NumberFormatException if balance is not correctly formatted`() {
        val presentationModel = AccountUI(
            id = 1,
            name = "Account",
            balance = "100.00.00",
            currency = "USD"
        )

        assertThrows(NumberFormatException::class.java) {
            accountsViewModel.presentationToDomain(presentationModel)
        }
    }

    @Test
    fun `presentationToDomain throws IllegalArgumentException if currency is invalid`() {
        val presentationModel = AccountUI(
            id = 1,
            name = "Account",
            balance = "100.00",
            currency = "US"
        )

        assertThrows(IllegalArgumentException::class.java) {
            accountsViewModel.presentationToDomain(presentationModel)
        }
    }

    @Test
    fun `domainToPresentation converts domain model to presentation model`() {
        val presentationModel = accountsViewModel.domainToPresentation(domainAccount)

        assert(presentationModel.name == "Account B")
        assert(presentationModel.balance == "230.50")
        assert(presentationModel.currency == "USD")
    }

    @Test
    fun `domainToPresentation handles decimal places in balance correctly`() {
        val accountWLessDecimals = domainAccount.copy(balance = "230.5".toBigDecimal())
        val accountWMoreDecimals = domainAccount.copy(balance = "230.500".toBigDecimal())
        val accountWDifferentCurrency = domainAccount.copy(
            balance = "230.500".toBigDecimal(),
            currency = Currency.getInstance("JPY")
        )

        val accountUIWLessDecimals = accountsViewModel.domainToPresentation(accountWLessDecimals)
        val accountUIWMoreDecimals = accountsViewModel.domainToPresentation(accountWMoreDecimals)
        val accountUIWDifferentCurrency =
            accountsViewModel.domainToPresentation(accountWDifferentCurrency)

        assert(accountUIWLessDecimals.balance == "230.50")
        assert(accountUIWMoreDecimals.balance == "230.50")
        assert(accountUIWDifferentCurrency.balance == "230")
    }

    @Test
    fun `domainToPresentation throws IllegalArgumentException`() {
        // Silver (XAG) is not a supported currency, but a currency instance can be created for it nonetheless
        val account = domainAccount.copy(currency = Currency.getInstance("XAG"))

        assertThrows(IllegalArgumentException::class.java) {
            accountsViewModel.domainToPresentation(account)
        }
    }
}
