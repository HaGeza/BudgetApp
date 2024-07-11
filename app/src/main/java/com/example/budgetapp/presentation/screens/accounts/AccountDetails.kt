package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.presentation.viewmodel.AccountsViewModel
import com.example.budgetapp.presentation.screens.BaseScreen

@Composable
fun AccountDetailsScreenContent(id: Int, modifier: Modifier) {
    val dataVM = hiltViewModel<AccountsViewModel>()
    val account = dataVM.getById(id)
}

@Composable
fun AccountDetailsScreen(topBar: @Composable () -> Unit, id: Int) {
    BaseScreen(
        content = { modifier ->
            AccountDetailsScreenContent(id = id, modifier = modifier)
        },
        topBar = topBar,
    )
}