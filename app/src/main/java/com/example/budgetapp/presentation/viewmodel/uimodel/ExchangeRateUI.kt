package com.example.budgetapp.presentation.viewmodel.uimodel

/** Presentation (UI) model for an exchange rate */
data class ExchangeRateUI(
    val id: Int = 0,
    val source: String,
    val other: String,
    val rate: Double,
)
