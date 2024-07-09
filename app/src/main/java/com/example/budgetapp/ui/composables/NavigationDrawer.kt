package com.example.budgetapp.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.budgetapp.navigation.Destination
import com.example.budgetapp.ui.composables.screens.HomeScreen
import com.example.budgetapp.ui.composables.screens.accounts.AccountCreationScreen
import com.example.budgetapp.ui.composables.screens.accounts.AccountDetailsScreen
import com.example.budgetapp.ui.composables.screens.accounts.AccountsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerButton(
    destination: Destination,
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Button(
        onClick = {
            navController.navigate(destination)
            if (drawerState.isOpen) scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(destination.name)
    }
}

@Composable
fun NavDrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    ModalDrawerSheet {
        Column {
            Text(text = "Title", modifier = Modifier.padding(16.dp))
            Divider()
            DrawerButton(Destination.Home, navController, drawerState, scope)
            DrawerButton(Destination.Accounts, navController, drawerState, scope)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationBar(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Open navigation drawer"
                )
            }
        },
        title = { Text("Budget App") }
    )
}

@Composable
fun NavigationDrawer() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val topBar: @Composable () -> Unit = { NavigationBar(drawerState, scope) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavDrawerContent(navController, drawerState, scope) },
    ) {
        NavHost(navController = navController, startDestination = Destination.Home) {
            composable<Destination.Home> {
                HomeScreen(topBar)
            }
            composable<Destination.Accounts> {
                AccountsScreen(
                    topBar,
                    navToDetails = { id: Int ->
                        navController.navigate(Destination.AccountDetails(id))
                    },
                    navToCreate = {
                        navController.navigate(Destination.AccountCreation)
                    }
                )
            }
            composable<Destination.AccountDetails> { backStackEntry ->
                val account: Destination.AccountDetails = backStackEntry.toRoute()
                AccountDetailsScreen(topBar, account.id)
            }
            composable<Destination.AccountCreation> {
                AccountCreationScreen(topBar)
            }
        }
    }
}

