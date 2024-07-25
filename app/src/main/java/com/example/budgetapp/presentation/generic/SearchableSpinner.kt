package com.example.budgetapp.presentation.generic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.budgetapp.Constants
import com.example.budgetapp.R

/**
 * Dropdown menu with a search bar to filter the options
 * @param options - List of options to display
 * @param value - The selected value
 * @param onOptionSelected - Function to call when an option is selected
 * @param text - Text to display in the top part of the dropdown
 * @param selectorModifier - Modifier for the dropdown
 * @param searchBarModifier - Modifier for the search bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableSpinner(
    options: List<String>,
    value: String,
    onOptionSelected: (String) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    selectorModifier: Modifier = Modifier,
    searchBarModifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var dropdownSize by remember { mutableStateOf(Size.Zero) }
    val dropdownState = rememberLazyListState()

    var query by remember { mutableStateOf("") }
    var filteredOptions by remember { mutableStateOf(options) }

    val context = LocalContext.current
    val searchPlaceholder = context.getString(R.string.searchable_spinner_search_placeholder)

    val search = { newQuery: String ->
        if (newQuery != query) {
            if (newQuery.isEmpty()) {
                filteredOptions = options
            } else if (newQuery.contains(query)) {
                filteredOptions = filteredOptions.filter {
                    it.contains(newQuery, ignoreCase = true)
                }
            } else {
                filteredOptions = options.filter {
                    it.contains(newQuery, ignoreCase = true)
                }
            }
            query = newQuery
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .onGloballyPositioned { dropdownSize = it.size.toSize() },
            value = value,
            onValueChange = { },
            readOnly = true,
            label = { Text(text) },
            trailingIcon = { TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = selectorModifier.exposedDropdownSize()
        ) {
            Column(
                modifier = Modifier
                    .size(dropdownSize.width.dp, dropdownSize.height.dp.times(2))
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = search,
                    onSearch = search,
                    active = expanded,
                    placeholder = { Text(searchPlaceholder) },
                    onActiveChange = { },
                    modifier = searchBarModifier.testTag(Constants.SPINNER_SEARCH_BAR_TAG),
                ) {
                    LazyColumn(
                        userScrollEnabled = true,
                        state = dropdownState,
                    ) {
                        items(filteredOptions.size) { index ->
                            DropdownMenuItem(
                                text = { Text(filteredOptions[index]) },
                                onClick = {
                                    onOptionSelected(filteredOptions[index])
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
        }
    }
}

/** Preview for [SearchableSpinner] */
@Preview
@Composable
fun SearchableSpinnerPreview() {
    SearchableSpinner(
        options = listOf("USD", "EUR", "GBP", "JPY", "CNY"),
        value = "USD",
        onOptionSelected = { },
        text = "Currency",
    )
}
