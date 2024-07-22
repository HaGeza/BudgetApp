package com.example.budgetapp.presentation.generic

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Field with an optional error message displayed below it
 * @param field - Field to display
 * @param errorMessage - Error message to display
 */
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
