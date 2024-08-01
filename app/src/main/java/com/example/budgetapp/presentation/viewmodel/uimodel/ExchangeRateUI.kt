package com.example.budgetapp.presentation.viewmodel.uimodel

/** Presentation (UI) model for an exchange rate */
data class ExchangeRateUI(
    val source: String,
    val other: String,
    val rate: String,
    val id: Int = 0,
)
