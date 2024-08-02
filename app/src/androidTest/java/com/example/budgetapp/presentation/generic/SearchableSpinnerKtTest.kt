package com.example.budgetapp.presentation.generic

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.budgetapp.Constants
import com.example.budgetapp.data.di.DataModule
import com.example.budgetapp.presentation.MainActivity
import com.example.budgetapp.ui.theme.BudgetAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class)
class SearchableSpinnerKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var options: List<String>
    private val dropdownText = "Option"

    @Before
    fun setUp() {
        options = listOf("Alpha", "Beta", "Gamma", "Delta")
        composeRule.activity.setContent {
            BudgetAppTheme {
                SearchableSpinner(
                    options = options,
                    value = "",
                    onOptionSelected = { },
                    text = dropdownText
                )
            }
        }
    }

    @Test
    fun `options are filtered on text change`() {
        val searchBar = composeRule.onNodeWithText(dropdownText)
        val optionAlpha = composeRule.onNodeWithText("Alpha")
        val optionBeta = composeRule.onNodeWithText("Beta")
        val optionGamma = composeRule.onNodeWithText("Gamma")
        val optionDelta = composeRule.onNodeWithText("Delta")

        // Options are not shown until the search bar is clicked
        optionAlpha.assertDoesNotExist()
        optionBeta.assertDoesNotExist()
        optionGamma.assertDoesNotExist()
        optionDelta.assertDoesNotExist()

        // All options are shown after dropdown is clicked and no query is entered
        searchBar.assertExists()
        searchBar.performClick()

        optionAlpha.assertExists()
        optionBeta.assertExists()
        optionGamma.assertExists()
        optionDelta.assertExists()

        // Get the search field
        val searchField = composeRule.onNodeWithTag(Constants.SPINNER_SEARCH_BAR_TAG)
        searchField.assertExists()
        val searchInputField = searchField.onChildAt(0)
        searchInputField.assertExists()

        // Options are filtered based on query 1
        searchInputField.performTextInput("lph")

        optionAlpha.assertExists()
        optionBeta.assertDoesNotExist()
        optionGamma.assertDoesNotExist()
        optionDelta.assertDoesNotExist()

        // Options are filtered based on query 2
        searchInputField.performTextClearance()
        searchInputField.performTextInput("ta")

        optionAlpha.assertDoesNotExist()
        optionBeta.assertExists()
        optionGamma.assertDoesNotExist()
        optionDelta.assertExists()
    }
}
