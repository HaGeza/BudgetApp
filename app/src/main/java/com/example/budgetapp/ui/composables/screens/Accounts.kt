package com.example.budgetapp.ui.composables.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.data.model.Account
import com.example.budgetapp.data.viewmodel.DataViewModel

@Composable
fun ShortAccountCard(modifier: Modifier, account: Account, navToDetails: (Int) -> Unit) {
    Card(modifier = modifier.padding(8.dp)) {
        Text(text = account.name)
        Text(text = "Balance: ${account.balance} ${account.currency.currencyCode}")
        Button(onClick = {
            navToDetails(account.id)
        }) {
            Text("Details")
        }
    }
}

@Composable
fun AccountsScreen(modifier: Modifier, navToDetails: (Int) -> Unit) {
    val dataVM = hiltViewModel<DataViewModel>()
    val accounts = dataVM.getAccounts().collectAsState(initial = emptyList())
    accounts.value.forEach() {
        ShortAccountCard(modifier, account = it, navToDetails)
    }
}

