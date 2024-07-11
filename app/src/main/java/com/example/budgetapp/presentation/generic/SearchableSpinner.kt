package com.example.budgetapp.presentation.generic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableSpinner(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val dropdownState = rememberLazyListState()

    var query by remember { mutableStateOf("") }
    var filteredOptions by remember { mutableStateOf(options) }

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
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = "",
            onValueChange = { },
            readOnly = true,
            label = { Text("Currency") },
            trailingIcon = { TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(
                modifier = Modifier.size(width = 200.dp, height = 300.dp),
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = search,
                    onSearch = search,
                    active = expanded,
                    placeholder = { Text("Search") },
                    onActiveChange = { }
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
