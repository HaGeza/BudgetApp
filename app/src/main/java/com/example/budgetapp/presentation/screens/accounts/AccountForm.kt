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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.presentation.generic.FieldWithErrorMessage
import com.example.budgetapp.presentation.generic.SearchableSpinner
import com.example.budgetapp.presentation.viewmodel.AccountFormViewModel
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI
import java.util.Currency

@Composable
fun AccountForm(modifier: Modifier, onSave: (AccountUI) -> Unit) {
    val currencies = Currency.getAvailableCurrencies().toList()

    val accountFormVM = hiltViewModel<AccountFormViewModel>()
    val formUIState = accountFormVM.formUIState
    val context = LocalContext.current

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        FieldWithErrorMessage(field = {
            OutlinedTextField(
                value = formUIState.name,
                onValueChange = { accountFormVM.onNameChanged(it) },
                label = { Text("Name") }
            )
        }, errorMessage = formUIState.nameError)

        FieldWithErrorMessage(field = {
            SearchableSpinner(
                options = currencies.map { it.currencyCode },
                value = formUIState.currency
            ) {
                accountFormVM.onCurrencyChanged(it)
            }
        }, errorMessage = formUIState.currencyError)

        FieldWithErrorMessage(field = {
            OutlinedTextField(
                value = formUIState.balance,
                onValueChange = { accountFormVM.onBalanceChanged(it) },
                label = { Text("Initial Balance") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }, errorMessage = formUIState.balanceError)

        Button(onClick = {
            if (accountFormVM.onSubmit()) {
                onSave(
                    AccountUI(
                        name = formUIState.name,
                        balance = formUIState.balance,
                        currency = formUIState.currency,
                    )
                )
                Toast.makeText(context, "Account saved", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Save")
        }
    }
}

@Composable
@Preview
fun AccountFormPreview() {
    AccountForm(Modifier, { account -> val pass = account })
}
