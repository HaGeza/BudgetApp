package com.example.budgetapp.ui.composables.screens.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.budgetapp.data.model.Account
import java.math.BigDecimal
import java.util.Currency
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountForm(modifier: Modifier, onSave: (Account) -> Unit) {
    var name by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf(Currency.getInstance(Locale.getDefault())) }
    var initialBalance by remember { mutableStateOf(BigDecimal.ZERO) }

    val currencies = java.util.Currency.getAvailableCurrencies().toList()

    Column(modifier = modifier) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        // TODO: dropdown not dropping down
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = { }
        ) {
            TextField(
                value = selectedCurrency.currencyCode,
                onValueChange = { },
                readOnly = true,
                label = { Text("Currency") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) }
            )
            ExposedDropdownMenu(
                expanded = false,
                onDismissRequest = { }
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(text = {
                        Text(currency.currencyCode)
                    }, onClick = {
                        selectedCurrency = currency
                    })
                }
            }
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
