package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.presentation.screens.BaseScreen
import com.example.budgetapp.presentation.viewmodel.AccountsViewModel
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI

@Composable
fun AccountCreationScreen(topBar: @Composable () -> Unit) {
    val accountsVM = hiltViewModel<AccountsViewModel>()
    val insertAccount = { account: AccountUI -> accountsVM.insert(account) }

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
