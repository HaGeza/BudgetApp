package com.example.budgetapp.presentation.screens.accounts

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R
import com.example.budgetapp.presentation.generic.FieldWithErrorMessage
import com.example.budgetapp.presentation.generic.SearchableSpinner
import com.example.budgetapp.presentation.viewmodel.AccountFormViewModel
import com.example.budgetapp.presentation.viewmodel.state.AccountFormUIState
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import java.util.Currency

/**
 * Form to create an account
 * @param modifier - Modifier for the form
 * @param onSubmit - Function to call when the form is submitted
 */
@Composable
fun AccountForm(
    modifier: Modifier,
    onSubmit: (AccountUI) -> Unit,
    accountFormVM: AccountFormViewModel?,
) {
    val currencies = Currency.getAvailableCurrencies().toList()

    val formUIState = if (accountFormVM != null) {
        accountFormVM.formUIState
    } else {
        AccountFormUIState()
    }
    val onSubmit = if (accountFormVM != null) {
        accountFormVM::onSubmit
    } else {
        { false }
    }
    val context = LocalContext.current

    // String resources
    val nameFieldCD = context.getString(R.string.account_form_name_field_cd)
    val nameFieldPH = context.getString(R.string.account_form_name_field_placeholder)
    val nameFIeldErrorCD = context.getString(R.string.account_form_name_field_error_cd)
    val currencyDropdownText = context.getString(R.string.account_form_currency_dropdown_text)
    val currencySelectorCD = context.getString(R.string.account_form_currency_selector_cd)
    val currencySearchCD = context.getString(R.string.account_form_currency_search_cd)
    val currencyErrorCD = context.getString(R.string.account_form_currency_selector_error_cd)
    val balanceFieldCD = context.getString(R.string.account_form_balance_field_cd)
    val balanceFieldPH = context.getString(R.string.account_form_balance_field_placeholder)
    val balanceFIeldErrorCD = context.getString(R.string.account_form_balance_field_error_cd)
    val saveButtonCD = context.getString(R.string.account_form_save_button_cd)
    val saveButtonText = context.getString(R.string.account_form_save_button_text)
    val cancelButtonCD = context.getString(R.string.account_form_cancel_button_cd)
    val cancelButtonText = context.getString(R.string.account_form_cancel_button_text)

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        // Name input field
        FieldWithErrorMessage(
            field = {
                OutlinedTextField(
                    value = formUIState.name,
                    onValueChange = { accountFormVM?.onNameChanged(it) },
                    label = { Text(nameFieldPH) },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = nameFieldCD
                        }
                )
            },
            errorMessage = formUIState.nameError,
            errorModifier = Modifier
                .padding(bottom = 8.dp)
                .padding(start = 16.dp)
                .semantics {
                    contentDescription = nameFIeldErrorCD
                }
        )

        // Currency input field
        FieldWithErrorMessage(field = {
            SearchableSpinner(
                options = currencies.map { it.currencyCode },
                value = formUIState.currency,
                onOptionSelected = { accountFormVM?.onCurrencyChanged(it) },
                dropdownModifier = Modifier
                    .semantics {
                        contentDescription = currencySelectorCD
                    },
                searchBarModifier = Modifier
                    .semantics {
                        contentDescription = currencySearchCD
                    },
                dropdownText = currencyDropdownText,
            )
        },
            errorMessage = formUIState.currencyError,
            errorModifier = Modifier
                .padding(bottom = 8.dp)
                .padding(start = 16.dp)
                .semantics {
                    contentDescription = currencyErrorCD
                }
        )

        // Balance input field
        FieldWithErrorMessage(field = {
            OutlinedTextField(
                value = formUIState.balance,
                onValueChange = { accountFormVM?.onBalanceChanged(it) },
                label = { Text(balanceFieldPH) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = balanceFieldCD
                    }
            )
        },
            errorMessage = formUIState.balanceError,
            errorModifier = Modifier
                .padding(bottom = 8.dp)
                .padding(start = 16.dp)
                .semantics {
                    contentDescription = balanceFIeldErrorCD
                }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = { /* Handle cancel action */ },
                modifier = Modifier
                    .semantics {
                        contentDescription = cancelButtonCD
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Text(cancelButtonText, fontSize = MaterialTheme.typography.bodyLarge.fontSize)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if (onSubmit()) {
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
                modifier = Modifier
                    .semantics {
                        contentDescription = saveButtonCD
                    }
            ) {
                Text(saveButtonText, fontSize = MaterialTheme.typography.bodyLarge.fontSize)
            }
        }
    }
}

/** Preview for the AccountForm composable */
@Composable
@Preview
fun AccountFormPreview() {
    AccountForm(Modifier, { account -> val pass = account }, null)
}
