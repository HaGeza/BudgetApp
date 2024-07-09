package com.example.budgetapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val route: String, val name: String) {
    @Serializable
    object Home : Destination("/", "Home")

    @Serializable
    object Accounts : Destination("/accounts", "Accounts")

    @Serializable
    data class AccountDetails(val id: Int) :
        Destination("/accounts/details/$id", "Account $id details")

    @Serializable
    object AccountCreation : Destination("/accounts/create", "Create Account")
}

