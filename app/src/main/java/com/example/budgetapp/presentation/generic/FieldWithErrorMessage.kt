package com.example.budgetapp.presentation.generic

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FieldWithErrorMessage(field: @Composable () -> Unit, errorMessage: String?) {
    field()
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
        )
    }
}
