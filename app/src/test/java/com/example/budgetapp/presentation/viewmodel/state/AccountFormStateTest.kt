package com.example.budgetapp.presentation.viewmodel.state

import androidx.test.filters.SmallTest
import com.example.budgetapp.domain.usecase.validator.InvalidCurrencyError
import com.example.budgetapp.domain.usecase.validator.InvalidNumberError
import com.example.budgetapp.domain.usecase.validator.RequiredFieldError
import com.example.budgetapp.domain.usecase.validator.ValidatedResult
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

@SmallTest
class AccountFormStateTest {
    lateinit var accountFormState: AccountFormState

    @Before
    fun setUp() {
        accountFormState = AccountFormState(
            nameResult = ValidatedResult("name"),
            currencyResult = ValidatedResult("currency"),
            balanceResult = ValidatedResult("balance")
        )
    }

    @Test
    fun `isValid returns true for result without errors`() {
        assertTrue(accountFormState.isValid())
    }

    @Test
    fun `isValid returns false for result with any error`() {
        val formStateWNameError =
            accountFormState.copy(nameResult = ValidatedResult("", RequiredFieldError()))
        assertFalse(formStateWNameError.isValid())

        val formStateWCurrencyError =
            accountFormState.copy(currencyResult = ValidatedResult("US", InvalidCurrencyError()))
        assertFalse(formStateWCurrencyError.isValid())

        val formStateWBalanceError =
            accountFormState.copy(balanceResult = ValidatedResult("three", InvalidNumberError()))
        assertFalse(formStateWBalanceError.isValid())
    }
}
