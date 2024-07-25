package com.example.budgetapp.presentation.generic

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.budgetapp.R
import com.example.budgetapp.R.string.navigation_drawer_button_cd
import com.example.budgetapp.R.string.top_bar_title
import com.example.budgetapp.presentation.navigation.Destination
import com.example.budgetapp.presentation.screens.HomeScreen
import com.example.budgetapp.presentation.screens.accounts.AccountCreation
import com.example.budgetapp.presentation.screens.accounts.AccountDetailsScreen
import com.example.budgetapp.presentation.screens.accounts.AccountsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Button for the navigation drawer. Navigates to the `destination` when clicked.
 * @param navTo Function to call when button is pressed
 * @param drawerState State of the drawer
 * @param scope Coroutine scope
 * @param title Text to display on the button
 * @param cd Content description for the button
 */
@Composable
fun DrawerButton(
    navTo: () -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
    title: String,
    cd: String,
) {
    Button(
        onClick = {
            navTo()
            if (drawerState.isOpen) scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = cd },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(title)
    }
}

/**
 * Content of the navigation drawer
 * @param navController Navigation controller
 * @param drawerState State of the drawer
 * @param scope Coroutine scope
 */
@Composable
fun NavDrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val context = LocalContext.current

    ModalDrawerSheet {
        Column {
            Text(text = "Title", modifier = Modifier.padding(16.dp))
            Divider()
            DrawerButton(
                { navController.navigate(Destination.Home) },
                drawerState,
                scope,
                context.getString(R.string.navigation_drawer_home_title),
                context.getString(R.string.navigation_drawer_home_cd),
            )
            DrawerButton(
                { navController.navigate(Destination.Accounts) },
                drawerState,
                scope,
                context.getString(R.string.navigation_drawer_accounts_title),
                context.getString(R.string.navigation_drawer_accounts_cd),
            )
        }
    }
}

/**
 * Top app bar for the application
 * @param drawerState State of the drawer
 * @param scope Coroutine scope
 * */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationBar(drawerState: DrawerState, scope: CoroutineScope) {
    val context = LocalContext.current
    val buttonCD = context.getString(navigation_drawer_button_cd)
    val barTitle = context.getString(top_bar_title)

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
                    contentDescription = buttonCD
                )
            }
        },
        title = { Text(barTitle) }
    )
}

/** Navigation drawer for the application */
@Preview
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
        // Navigation graph
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
                AccountCreation(
                    topBar,
                    navBack = { navController.popBackStack() }
                )
            }
        }
    }
}
