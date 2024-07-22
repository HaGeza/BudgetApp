package com.example.budgetapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Base screen for all screens in the application
 * @param content - Content of the screen
 * @param topBar - Top bar of the screen
 * @param fab - Floating action button
 */
@Composable
fun BaseScreen(
    content: @Composable (Modifier) -> Unit,
    topBar: @Composable () -> Unit,
    fab: @Composable (() -> Unit)? = null,
) {
    Scaffold(
        topBar = { topBar() },
        floatingActionButton = {
            fab?.invoke()
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}
