package com.example.budgetapp.presentation.viewmodel.uimodel

/** Presentation (UI) model for an account */
data class AccountUI(
    val name: String,
    val balance: String,
    val currency: String,
    val id: Int = 0,
)
