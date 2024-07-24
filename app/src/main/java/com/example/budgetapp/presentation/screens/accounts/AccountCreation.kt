package com.example.budgetapp.presentation.screens.accounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetapp.presentation.screens.BaseScreen
import com.example.budgetapp.presentation.viewmodel.validationVM.AccountFormViewModel
import com.example.budgetapp.presentation.viewmodel.dataVM.AccountsViewModel
import com.example.budgetapp.presentation.viewmodel.uimodel.AccountUI

/**
 * Screen to create a new account
 * @param topBar - Top bar of the screen to show
 */
@Composable
fun AccountCreationScreen(
    topBar: @Composable () -> Unit,
    navBack: () -> Unit,
) {
    val accountsVM = hiltViewModel<AccountsViewModel>()
    val accountFormVM = hiltViewModel<AccountFormViewModel>()
    val insertAccount = { account: AccountUI -> accountsVM.insert(account) }

    BaseScreen(
        content = { modifier ->
            AccountForm(
                modifier = modifier,
                onSaveAccount = insertAccount,
                navBack = navBack,
                accountFormVM = accountFormVM,
            )
        },
        topBar = topBar,
    )
}
