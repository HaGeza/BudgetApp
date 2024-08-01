package com.example.budgetapp.presentation.screens.accounts

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
import com.example.budgetapp.presentation.screens.BaseScreen
import com.example.budgetapp.presentation.viewmodel.dataVM.AccountsViewModel
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI

@Composable
fun ShortAccountCard(account: AccountUI, navToDetails: ((Int) -> Unit)? = null) {
    Card(modifier = Modifier.padding(8.dp)) {
        Text(text = account.name)
        Text(text = "Balance: ${account.balance} ${account.currency}")
        Button(onClick = {
            navToDetails?.invoke(account.id)
        }) {
            Text("Details")
        }
    }
}

@Composable
fun AccountsScreenContent(
    accounts: List<AccountUI>,
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
            AccountUI("Account 1", "105.00", "USD", 1),
            AccountUI("Account 2", "200.99", "USD", 2)
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
