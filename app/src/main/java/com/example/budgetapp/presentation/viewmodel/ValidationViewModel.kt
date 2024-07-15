package com.example.budgetapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.NonEmptyStringValidator
import com.example.budgetapp.domain.usecase.validator.NumberValidator
import com.example.budgetapp.domain.usecase.validator.ValidatedResult
import com.example.budgetapp.presentation.validation.event.AccountFormEvent
import com.example.budgetapp.presentation.validation.state.AccountFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountFormValidatorVM @Inject constructor(
    private val nameValidator: NonEmptyStringValidator,
    private val balanceValidator: NumberValidator,
    private val currencyValidator: CurrencyValidator,
) : ViewModel() {
    var formState by mutableStateOf(AccountFormState())

    private val validationEventChannel = Channel<ValidationSignal>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AccountFormEvent) {
        when (event) {
            is AccountFormEvent.NameChanged -> {
                formState = formState.copy(nameResult = ValidatedResult(event.name))
            }

            is AccountFormEvent.CurrencyChanged -> {
                formState = formState.copy(nameResult = ValidatedResult(event.currency))
            }

            is AccountFormEvent.BalanceChanged -> {
                formState = formState.copy(nameResult = ValidatedResult(event.balance))
            }

            is AccountFormEvent.Submit -> {
                formState = formState.copy(
                    nameResult = nameValidator(formState.nameResult.value),
                    balanceResult = balanceValidator(formState.balanceResult.value),
                    currencyResult = currencyValidator(formState.currencyResult.value),
                )

                if (formState.isValid()) {
                    viewModelScope.launch {
                        validationEventChannel.send(ValidationSignal.Success)
                    }
                }
            }
        }
    }
}
