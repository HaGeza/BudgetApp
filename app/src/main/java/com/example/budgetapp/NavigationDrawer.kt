package com.example.budgetapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ContentAlpha
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier) {
    Text("Home Screen", modifier = modifier)
}

@Composable
fun AccountsScreen(modifier: Modifier) {
    Text("Accounts Screen", modifier = modifier)
}

@Composable
fun DrawerButton(
    text: String,
    destination: String,
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val start = navController.currentBackStackEntryAsState().value?.destination?.route

    val startIsDestination = start == destination
    val buttonColors = if (startIsDestination) {
        ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    } else {
        ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium))
    }

    Button(
        onClick = {
            navController.navigate(destination)
            if (drawerState.isOpen) scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = buttonColors
    ) {
        Text(text)
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
            DrawerButton("Home", "home", navController, drawerState, scope)
            DrawerButton("Accounts", "accounts", navController, drawerState, scope)
        }
    }
}

@Composable
fun SelectedScreen(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(modifier)
        }
        composable("accounts") {
            AccountsScreen(modifier)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavDrawerContent(navController, drawerState, scope) },
    ) {
        Scaffold(
            topBar = {
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
            },
            floatingActionButton = {
                ExpandableFab()
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            SelectedScreen(navController, Modifier.padding(innerPadding))
        }
    }
}

