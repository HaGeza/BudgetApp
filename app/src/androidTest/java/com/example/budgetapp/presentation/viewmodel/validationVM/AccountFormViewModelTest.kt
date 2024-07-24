package com.example.budgetapp.presentation.viewmodel.validationVM

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.InvalidCurrencyError
import com.example.budgetapp.domain.usecase.validator.InvalidNumberError
import com.example.budgetapp.domain.usecase.validator.NumberValidator
import com.example.budgetapp.domain.usecase.validator.RequiredFieldError
import com.example.budgetapp.domain.usecase.validator.StringValidator
import com.example.budgetapp.domain.usecase.validator.ValidatedResult
import com.example.budgetapp.presentation.viewmodel.state.AccountFormUIState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

@SmallTest
class AccountFormViewModelTest {
    lateinit var formViewModel: AccountFormViewModel
    lateinit var context: Context
    private val nameValidator = mockk<StringValidator>()
    private val currencyValidator = mockk<CurrencyValidator>()
    private val balanceValidator = mockk<NumberValidator>()

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        formViewModel = AccountFormViewModel(
            nameValidator = nameValidator,
            currencyValidator = currencyValidator,
            balanceValidator = balanceValidator,
            context = context
        )
    }

    @Test
    fun `onNameChanged updates formUIState correctly`() {
        formViewModel.onNameChanged("John Doe")
        assertEquals("John Doe", formViewModel.formUIState.name)
    }

    @Test
    fun `onCurrencyChanged updates formUIState correctly`() {
        formViewModel.onCurrencyChanged("USD")
        assertEquals("USD", formViewModel.formUIState.currency)
    }

    @Test
    fun `onBalanceChanged updates formUIState correctly`() {
        formViewModel.onBalanceChanged("1000")
        assertEquals("1000", formViewModel.formUIState.balance)
    }

    @Test
    fun `onSubmit returns true for valid form`() {
        formViewModel.formUIState = AccountFormUIState(
            name = "John Doe",
            currency = "USD",
            balance = "1000.00",
            nameError = null,
            currencyError = null,
            balanceError = null
        )

        every { nameValidator("John Doe") } returns ValidatedResult("John Doe", null)
        every { currencyValidator("USD") } returns ValidatedResult("USD", null)
        every { balanceValidator("1000.00") } returns ValidatedResult("1000.00", null)

        val valid = formViewModel.onSubmit()

        assertTrue(valid)

        assertEquals(formViewModel.formUIState.name, "John Doe")
        assertEquals(formViewModel.formUIState.currency, "USD")
        assertEquals(formViewModel.formUIState.balance, "1000.00")

        assertNull(formViewModel.formUIState.nameError)
        assertNull(formViewModel.formUIState.currencyError)
        assertNull(formViewModel.formUIState.balanceError)
    }

    @Test
    fun `onSubmit returns false and sets error strings for invalid form`() {
        formViewModel.formUIState = AccountFormUIState(
            name = "",
            currency = "USDollar",
            balance = "1000.0x0d",
            nameError = null,
            currencyError = null,
            balanceError = null
        )

        every { nameValidator("") } returns ValidatedResult("", RequiredFieldError())
        every { currencyValidator("USDollar") } returns ValidatedResult(
            "USDollar",
            InvalidCurrencyError()
        )
        every { balanceValidator("1000.0x0d") } returns ValidatedResult(
            "1000.0x0d",
            InvalidNumberError()
        )

        val valid = formViewModel.onSubmit()

        assertFalse(valid)

        assertEquals(formViewModel.formUIState.name, "")
        assertEquals(formViewModel.formUIState.currency, "USDollar")
        assertEquals(formViewModel.formUIState.balance, "1000.0x0d")

        assertNotNull(formViewModel.formUIState.nameError)
        assertNotNull(formViewModel.formUIState.currencyError)
        assertNotNull(formViewModel.formUIState.balanceError)
    }
}
