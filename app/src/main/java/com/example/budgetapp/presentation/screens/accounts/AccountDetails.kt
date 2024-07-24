package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.presentation.screens.BaseScreen
import com.example.budgetapp.presentation.viewmodel.dataVM.AccountsViewModel

/**
 * PLACEHOLDER
 * Content of the account details screen
 * @param id - ID of the account to display
 * @param modifier - Modifier for the content
 */
@Composable
fun AccountDetailsScreenContent(id: Int, modifier: Modifier) {
    val dataVM = hiltViewModel<AccountsViewModel>()
    val account = dataVM.getById(id)
}

/**
 * PLACEHOLDER
 * Screen to display the details of an account
 * @param topBar - Top bar of the screen to show
 * @param id - ID of the account to display
 */
@Composable
fun AccountDetailsScreen(topBar: @Composable () -> Unit, id: Int) {
    BaseScreen(
        content = { modifier ->
            AccountDetailsScreenContent(id = id, modifier = modifier)
        },
        topBar = topBar,
    )
}
