package com.example.budgetapp.ui.composables.screens.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.data.model.Account
import com.example.budgetapp.data.viewmodel.AccountsViewModel
import com.example.budgetapp.ui.composables.screens.BaseScreen

@Composable
fun AccountCreationScreen(topBar: @Composable () -> Unit) {
    val accountsVM = hiltViewModel<AccountsViewModel>()
    val insertAccount = { account: Account -> accountsVM.insert(account) }

    BaseScreen(
        content = { modifier ->
            AccountForm(
                modifier,
                onSave = insertAccount
            )
        },
        topBar = topBar,
    )
}