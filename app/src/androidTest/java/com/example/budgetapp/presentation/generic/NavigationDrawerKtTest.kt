package com.example.budgetapp.presentation.generic

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.budgetapp.R
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
class NavigationDrawerKtTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var context: Context


    @Before
    fun setUp() {
        composeRule.activity.setContent {
            BudgetAppTheme {
                NavigationDrawer()
            }
        }
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `topBar button open drawer`() {
        val drawerButtonCD = context.getString(R.string.navigation_drawer_button_cd)
        val drawerButton = composeRule.onNodeWithContentDescription(drawerButtonCD)
        drawerButton.assertExists()
        drawerButton.performClick()

        for (cd in listOf(
            context.getString(R.string.navigation_drawer_home_cd),
            context.getString(R.string.navigation_drawer_accounts_cd),
        )) {
            val drawerItem = composeRule.onNodeWithContentDescription(cd)
            drawerItem.assertExists()
        }
    }
}
