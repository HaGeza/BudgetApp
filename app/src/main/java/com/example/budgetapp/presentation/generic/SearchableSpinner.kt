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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableSpinner(
    options: List<String>,
    value: String,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val dropdownState = rememberLazyListState()

    var query by remember { mutableStateOf("") }
    var filteredOptions by remember { mutableStateOf(options) }

    val context = LocalContext.current
    val currencySelectorCD = context.getString(R.string.account_form_currency_selector_cd)
    val currencySearchCD = context.getString(R.string.account_form_currency_search_cd)

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
            value = value,
            onValueChange = { },
            readOnly = true,
            label = { Text("Currency") },
            trailingIcon = { TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.semantics {
                contentDescription = currencySelectorCD
            }
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
                    onActiveChange = { },
                    modifier = Modifier.semantics {
                        contentDescription = currencySearchCD
                    }
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
