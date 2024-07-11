package com.example.budgetapp.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.budgetapp.presentation.ExpandableFab

@Composable
fun HomeScreen(topBar: @Composable () -> Unit) {
    BaseScreen(
        content = { modifier ->
            Text("Home Screen", modifier = modifier)
        },
        topBar = topBar,
        fab = {
            ExpandableFab()
        },
    )
}

