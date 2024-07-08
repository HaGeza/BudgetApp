package com.example.budgetapp.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.budgetapp.navigation.Destination

@Composable
fun SelectedScreen(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreen(modifier)
        }
        composable<Destination.Accounts> {
            AccountsScreen(
                modifier,
                { id: Int -> navController.navigate(Destination.AccountDetails(id)) })
        }
        composable<Destination.AccountDetails> { backStackEntry ->
            val account: Destination.AccountDetails = backStackEntry.toRoute()
            AccountDetailsScreen(account.id, modifier)
        }
    }
}

