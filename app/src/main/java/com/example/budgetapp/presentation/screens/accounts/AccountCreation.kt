package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.presentation.viewmodel.AccountsViewModel
import com.example.budgetapp.presentation.screens.BaseScreen

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