package com.example.budgetapp.ui.composables.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.data.model.Account
import com.example.budgetapp.data.viewmodel.DataViewModel
import java.math.BigDecimal
import java.util.Currency

@Composable
fun ShortAccountCard(account: Account, navToDetails: (Int) -> Unit) {
    Card(modifier = Modifier.padding(8.dp)) {
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
fun AccountsScreenContentWData(
    accounts: List<Account>,
    navToDetails: (Int) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(modifier) {
        items(accounts.size) { index ->
            ShortAccountCard(account = accounts[index], navToDetails)
        }
    }
}

@Composable
fun AccountsScreenContent(navToDetails: (Int) -> Unit, modifier: Modifier) {
    val dataVM = hiltViewModel<DataViewModel>()
    val accounts = dataVM.getAccounts().collectAsState(initial = emptyList())
    AccountsScreenContentWData(accounts.value, navToDetails, modifier)
}

@Preview
@Composable
fun AccountScreenContentPreview() {
    AccountsScreenContentWData(
        accounts = listOf(
            Account(1, "Account 1", BigDecimal.valueOf(10050, 2), Currency.getInstance("USD")),
            Account(2, "Account 2", BigDecimal.valueOf(20000, 2), Currency.getInstance("USD"))
        ), navToDetails = { id: Int -> (print("Navigate to account $id details")) }, Modifier
    )
}


@Composable
fun AccountsScreen(topBar: @Composable () -> Unit, navToDetails: (Int) -> Unit) {
    BaseScreen(
        content = { modifier ->
            AccountsScreenContent(navToDetails, modifier)
        },
        topBar = topBar,
        fab = null,
    )
}
