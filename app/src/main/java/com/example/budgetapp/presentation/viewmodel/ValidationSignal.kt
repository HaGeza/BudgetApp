package com.example.budgetapp.presentation.viewmodel

sealed class ValidationSignal {
    object Success : ValidationSignal()
}
