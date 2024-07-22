package com.example.budgetapp.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.budgetapp.presentation.generic.ExpandableFab

/**
 * Home screen of the application
 * @param topBar - Top bar of the screen to show
 */
@Composable
fun HomeScreen(topBar: @Composable () -> Unit) {
    BaseScreen(
        content = { modifier ->
            Text("PLACEHOLDER Home Screen", modifier = modifier)
        },
        topBar = topBar,
        fab = {
            ExpandableFab()
        },
    )
}
