package com.example.budgetapp.presentation.generic

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Field with an optional error message displayed below it
 * @param field Field to display
 * @param errorMessage Error message to display
 * @param modifier Modifier for the component
 * @param errorModifier Modifier for the error field
 */
@Composable
fun FieldWithErrorMessage(
    field: @Composable () -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        field()
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = errorModifier,
            )
        }
    }
}

@Preview
@Composable
fun FieldWithErrorMessagePreview() {
    FieldWithErrorMessage(
        field = {
            Text("Field")
        },
        errorMessage = "Error",
    )
}
