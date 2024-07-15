package com.example.budgetapp.presentation.validation.event

sealed class AccountFormEvent {
    data class NameChanged(val name: String) : AccountFormEvent()
    data class CurrencyChanged(val currency: String) : AccountFormEvent()
    data class BalanceChanged(val balance: String) : AccountFormEvent()
    object Submit : AccountFormEvent()
}
