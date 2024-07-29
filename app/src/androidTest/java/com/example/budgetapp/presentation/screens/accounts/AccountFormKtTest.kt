package com.example.budgetapp.presentation.screens.accounts

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.R
import com.example.budgetapp.data.di.DataModule
import com.example.budgetapp.presentation.MainActivity
import com.example.budgetapp.presentation.navigation.Destination
import com.example.budgetapp.presentation.viewmodel.validationVM.AccountFormViewModel
import com.example.budgetapp.ui.theme.BudgetAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class)
class AccountFormKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var context: Context

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            BudgetAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home
                ) {
                    composable<Destination.Home> {
                        AccountForm(
                            onSaveAccount = { },
                            navBack = { },
                            accountFormVM = hiltViewModel<AccountFormViewModel>(),
                            modifier = Modifier,
                        )
                    }
                }
            }
        }
        context = composeRule.activity.applicationContext
    }

    fun inputName(name: String) {
        val nameFieldCD = context.getString(R.string.account_form_name_field_cd)
        val nameField = composeRule.onNodeWithContentDescription(nameFieldCD)

        nameField.assertExists()
        nameField.performTextInput(name)
        nameField.assert(hasText(name))
    }

    fun selectCurrency(currency: String) {
        val currencyDropdownCD = context.getString(R.string.account_form_currency_dropdown_cd)
        val currencyDropdown = composeRule.onNodeWithContentDescription(currencyDropdownCD)
        currencyDropdown.assertExists()
        currencyDropdown.performClick()

        val currencySearchCD = context.getString(R.string.account_form_currency_search_cd)
        val currencySearch = composeRule.onNodeWithContentDescription(currencySearchCD)
        currencySearch.assertExists()

        val searchField = currencySearch.onChildAt(0)
        searchField.assertExists()
        searchField.performTextInput(currency)

        val currencyItem = composeRule.onAllNodesWithText(currency)[1]
        currencyItem.assertExists()
        currencyItem.performClick()
    }

    fun inputBalance(balance: String) {
        val balanceFieldCD = context.getString(R.string.account_form_balance_field_cd)
        val balanceField = composeRule.onNodeWithContentDescription(balanceFieldCD)

        balanceField.assertExists()
        balanceField.performTextInput(balance)
        balanceField.assert(hasText(balance))
    }

    fun submitForm() {
        val saveButtonCD = context.getString(R.string.account_form_save_button_cd)
        val saveButton = composeRule.onNodeWithContentDescription(saveButtonCD)

        saveButton.assertExists()
        saveButton.performClick()
    }

    fun getNameError(): SemanticsNodeInteraction {
        val nameErrorCD = context.getString(R.string.account_form_name_field_error_cd)
        return composeRule.onNodeWithContentDescription(nameErrorCD)
    }

    fun getCurrencyError(): SemanticsNodeInteraction {
        val currencyErrorCD = context.getString(R.string.account_form_currency_selector_error_cd)
        return composeRule.onNodeWithContentDescription(currencyErrorCD)
    }

    fun getBalanceError(): SemanticsNodeInteraction {
        val balanceFIeldErrorCD = context.getString(R.string.account_form_balance_field_error_cd)
        return composeRule.onNodeWithContentDescription(balanceFIeldErrorCD)
    }

    @Test
    fun `valid inputs are accepted`() {
        inputName("Account Name")
        selectCurrency("USD")
        inputBalance("1000")
        submitForm()

        getNameError().assertDoesNotExist()
        getCurrencyError().assertDoesNotExist()
        getBalanceError().assertDoesNotExist()
    }

    @Test
    fun `invalid inputs produce appropriate error messages`() {
        inputName("")
        // Do not select currency
        inputBalance("1.1.1")
        submitForm()

        val nameError = getNameError()
        nameError.assertExists()
        val nameErrorText = context.getString(R.string.input_required)
        nameError.assert(hasText(nameErrorText))

        val currencyError = getCurrencyError()
        currencyError.assertExists()
        val currencyErrorText = context.getString(R.string.input_required)
        currencyError.assert(hasText(currencyErrorText))

        val balanceError = getBalanceError()
        balanceError.assertExists()
        val balanceErrorText = context.getString(R.string.input_number_invalid)
        balanceError.assert(hasText(balanceErrorText))
    }
}
