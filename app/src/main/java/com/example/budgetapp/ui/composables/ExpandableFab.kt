package com.example.budgetapp.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFab() {
    var isExpanded by remember { mutableStateOf(false) }
    val icon = if (isExpanded) Icons.Filled.Close else Icons.Filled.Add

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(visible = isExpanded) {
            Column {
                FloatingActionButton(onClick = { /* Handle action 1 */ }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                FloatingActionButton(onClick = { /* Handle action 2 */ }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
                FloatingActionButton(onClick = { /* Handle action 3 */ }) {
                    Icon(Icons.Filled.Share, contentDescription = "Share")
                }
            }
        }
        FloatingActionButton(onClick = { isExpanded = !isExpanded }) {
            Icon(icon, contentDescription = if (isExpanded) "Collapse" else "Expand")
        }
    }
}
