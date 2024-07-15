package com.example.budgetapp.presentation.screens.accounts

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.presentation.generic.SearchableSpinner
import com.example.budgetapp.presentation.viewmodel.AccountFormValidatorVM
import com.example.budgetapp.presentation.viewmodel.ValidationSignal
import java.math.BigDecimal
import java.util.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountForm(modifier: Modifier, onSave: (Account) -> Unit) {
    val currencies = Currency.getAvailableCurrencies().toList()

    var name by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf(currencies[0]) }
    var balance by remember { mutableStateOf(BigDecimal.ZERO) }

    val validatorVM = hiltViewModel<AccountFormValidatorVM>()
    val formState = validatorVM.formState
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        validatorVM.validationEvents.collect { event ->
            when (event) {
                is ValidationSignal.Success -> {
                    onSave(
                        Account(
                            name = name,
                            balance = balance,
                            currency = selectedCurrency
                        )
                    )
                    Toast.makeText(context, "Account saved", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
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
            value = balance.toString(),
            onValueChange = { balance = BigDecimal(it) },
            label = { Text("Initial Balance") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // TODO: application does not navigate back on save
        Button(onClick = {
            onSave(Account(name = name, balance = balance, currency = selectedCurrency))
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
