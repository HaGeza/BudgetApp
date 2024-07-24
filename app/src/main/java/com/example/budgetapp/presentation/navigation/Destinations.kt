package com.example.budgetapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val route: String) {
    @Serializable
    object Home : Destination("/")

    @Serializable
    object Accounts : Destination("/accounts")

    @Serializable
    data class AccountDetails(val id: Int) :
        Destination("/accounts/details/$id")

    @Serializable
    object AccountCreation : Destination("/accounts/create")
}
