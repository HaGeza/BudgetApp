package com.example.budgetapp.ui.composables.screens.accounts

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
import com.example.budgetapp.data.viewmodel.AccountsViewModel
import com.example.budgetapp.ui.composables.screens.BaseScreen
import java.math.BigDecimal
import java.util.Currency

@Composable
fun ShortAccountCard(account: Account, navToDetails: ((Int) -> Unit)? = null) {
    Card(modifier = Modifier.padding(8.dp)) {
        Text(text = account.name)
        Text(text = "Balance: ${account.balance} ${account.currency.currencyCode}")
        Button(onClick = {
            navToDetails?.invoke(account.id)
        }) {
            Text("Details")
        }
    }
}

@Composable
fun AccountsScreenContent(
    accounts: List<Account>,
    modifier: Modifier,
    navToDetails: ((Int) -> Unit)? = null,
    navToCreate: (() -> Unit)? = null,
) {
    LazyColumn(modifier) {
        items(accounts.size) { index ->
            ShortAccountCard(account = accounts[index], navToDetails)
        }
        item {
            Button(onClick = {
                navToCreate?.invoke()
            }) {
                Text("Add Account")
            }

        }
    }
}

@Preview
@Composable
fun AccountScreenContentPreview() {
    AccountsScreenContent(
        accounts = listOf(
            Account(1, "Account 1", BigDecimal.valueOf(10050, 2), Currency.getInstance("USD")),
            Account(2, "Account 2", BigDecimal.valueOf(20000, 2), Currency.getInstance("USD"))
        ), Modifier
    )
}


@Composable
fun AccountsScreen(
    topBar: @Composable () -> Unit,
    navToDetails: (Int) -> Unit,
    navToCreate: () -> Unit,
) {
    val accountsVM = hiltViewModel<AccountsViewModel>()
    val accounts = accountsVM.getAll().collectAsState(initial = emptyList())

    BaseScreen(
        content = { modifier ->
            AccountsScreenContent(
                accounts.value,
                modifier,
                navToDetails,
                navToCreate,
            )
        },
        topBar = topBar,
    )
}
