package com.example.budgetapp.presentation.screens.accounts

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.R.string.account_form_balance_field_cd
import com.example.budgetapp.R.string.account_form_balance_field_placeholder
import com.example.budgetapp.R.string.account_form_name_field_cd
import com.example.budgetapp.R.string.account_form_name_field_placeholder
import com.example.budgetapp.R.string.account_form_save_button_cd
import com.example.budgetapp.R.string.account_form_save_button_text
import com.example.budgetapp.presentation.generic.FieldWithErrorMessage
import com.example.budgetapp.presentation.generic.SearchableSpinner
import com.example.budgetapp.presentation.viewmodel.AccountFormViewModel
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import java.util.Currency

/**
 * Form to create an account
 * @param modifier - Modifier for the form
 * @param onSubmit - Function to call when the form is submitted
 */
@Composable
fun AccountForm(modifier: Modifier, onSubmit: (AccountUI) -> Unit) {
    val currencies = Currency.getAvailableCurrencies().toList()

    val accountFormVM = hiltViewModel<AccountFormViewModel>()
    val formUIState = accountFormVM.formUIState
    val context = LocalContext.current

    // String resources
    val nameFieldCD = context.getString(account_form_name_field_cd)
    val nameFieldPH = context.getString(account_form_name_field_placeholder)
    val balanceFieldCD = context.getString(account_form_balance_field_cd)
    val balanceFieldPH = context.getString(account_form_balance_field_placeholder)
    val saveButtonCD = context.getString(account_form_save_button_cd)
    val saveButtonText = context.getString(account_form_save_button_text)

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        // Name input field
        FieldWithErrorMessage(field = {
            OutlinedTextField(
                value = formUIState.name,
                onValueChange = { accountFormVM.onNameChanged(it) },
                label = { Text(nameFieldPH) },
                modifier = Modifier.semantics {
                    contentDescription = nameFieldCD
                }
            )
        }, errorMessage = formUIState.nameError)

        // Currency input field
        FieldWithErrorMessage(field = {
            SearchableSpinner(
                options = currencies.map { it.currencyCode },
                value = formUIState.currency
            ) {
                accountFormVM.onCurrencyChanged(it)
            }
        }, errorMessage = formUIState.currencyError)

        // Balance input field
        FieldWithErrorMessage(field = {
            OutlinedTextField(
                value = formUIState.balance,
                onValueChange = { accountFormVM.onBalanceChanged(it) },
                label = { Text(balanceFieldPH) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.semantics {
                    contentDescription = balanceFieldCD
                }
            )
        }, errorMessage = formUIState.balanceError)

        // Submit button
        Button(onClick = {
            if (accountFormVM.onSubmit()) {
                onSubmit(
                    AccountUI(
                        name = formUIState.name,
                        balance = formUIState.balance,
                        currency = formUIState.currency,
                    )
                )
                Toast.makeText(context, "PLACEHOLDER Account saved", Toast.LENGTH_SHORT).show()
            }
        },
            modifier = Modifier.semantics {
                contentDescription = saveButtonCD
            }) {
            Text(saveButtonText)
        }
    }
}

/** Preview for the AccountForm composable */
@Composable
@Preview
fun AccountFormPreview() {
    AccountForm(Modifier, { account -> val pass = account })
}
