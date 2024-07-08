package com.example.budgetapp.navigation

import androidx.navigation.NavController

class Navigator(navController: NavController) {
    val navigate: (Destination) -> Unit = { destination ->
        navController.navigate(destination)
    }
}