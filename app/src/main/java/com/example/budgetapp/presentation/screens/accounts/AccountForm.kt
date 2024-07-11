package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.presentation.generic.SearchableSpinner
import java.math.BigDecimal
import java.util.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountForm(modifier: Modifier, onSave: (Account) -> Unit) {
    val currencies = Currency.getAvailableCurrencies().toList()

    var name by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf(currencies[0]) }
    var initialBalance by remember { mutableStateOf(BigDecimal.ZERO) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        SearchableSpinner(options = currencies.map { it.currencyCode }) {
            selectedCurrency = Currency.getInstance(it)
        }

        // TODO: application fails on non-integer or empty input
        OutlinedTextField(
            value = initialBalance.toString(),
            onValueChange = { initialBalance = BigDecimal(it) },
            label = { Text("Initial Balance") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // TODO: application does not navigate back on save
        Button(onClick = {
            onSave(Account(name = name, balance = initialBalance, currency = selectedCurrency))
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