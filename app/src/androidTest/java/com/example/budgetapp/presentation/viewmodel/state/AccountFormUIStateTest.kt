package com.example.budgetapp.presentation.viewmodel.state

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.budgetapp.R
import com.example.budgetapp.domain.usecase.validator.InvalidCurrencyError
import com.example.budgetapp.domain.usecase.validator.InvalidNumberError
import com.example.budgetapp.domain.usecase.validator.RequiredFieldError
import com.example.budgetapp.domain.usecase.validator.ValidatedResult
import org.junit.Before
import org.junit.Test

@SmallTest
class AccountFormUIStateTest {
    lateinit var context: Context
    lateinit var accountFormState: AccountFormState

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        accountFormState = AccountFormState(
            nameResult = ValidatedResult("Name"),
            currencyResult = ValidatedResult("USD"),
            balanceResult = ValidatedResult("123.45")
        )
    }

    @Test
    fun `toFormState converts UI form state to domain layer form state`() {
        val accountFormUIState = AccountFormUIState(
            name = "Name",
            currency = "USD",
            balance = "123.45",
            nameError = null,
            currencyError = null,
            balanceError = null,
        )

        val result = accountFormUIState.toFormState()

        assert(result.nameResult == ValidatedResult("Name", null))
        assert(result.currencyResult == ValidatedResult("USD", null))
        assert(result.balanceResult == ValidatedResult("123.45", null))
    }

    @Test
    fun `fromFormState converts domain layer form state to UI form state`() {
        val result = AccountFormUIState().fromFormState(accountFormState, context)

        assert(result.name == "Name")
        assert(result.currency == "USD")
        assert(result.balance == "123.45")
        assert(result.nameError == null)
        assert(result.currencyError == null)
        assert(result.balanceError == null)
    }

    @Test
    fun `fromFormState gets correct error messages`() {
        val formStateWErrors = AccountFormState(
            nameResult = ValidatedResult(null, RequiredFieldError()),
            currencyResult = ValidatedResult("EURO", InvalidCurrencyError()),
            balanceResult = ValidatedResult("fifty-four", InvalidNumberError())
        )

        val result = AccountFormUIState().fromFormState(formStateWErrors, context)

        assert(result.name == "")
        assert(result.currency == "EURO")
        assert(result.balance == "fifty-four")
        assert(result.nameError == context.getString(R.string.input_required))
        assert(result.currencyError == context.getString(R.string.input_invalid))
        assert(result.balanceError == context.getString(R.string.input_number_invalid))
    }
}
