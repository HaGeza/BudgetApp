package com.example.budgetapp.presentation.screens.accounts

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.budgetapp.R.string.account_form_name_field_cd
import com.example.budgetapp.data.di.DataModule
import com.example.budgetapp.presentation.MainActivity
import com.example.budgetapp.presentation.navigation.Destination
import com.example.budgetapp.ui.theme.BudgetAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class)
class AccountCreationScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var context: Context

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            BudgetAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destination.AccountCreation
                ) {
                    composable<Destination.AccountCreation> {
                         AccountCreationScreen(topBar = { }, navBack = { })
                    }
                }
            }
        }
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testAccountCreationScreen() {
        val nameFieldCD = context.getString(account_form_name_field_cd)
        val textField = composeRule.onNodeWithContentDescription(nameFieldCD)

        textField.assertExists()
        textField.performTextInput("Test Account")
        textField.assert(hasText("Test Account"))

        // TODO: Add more tests
    }
}
