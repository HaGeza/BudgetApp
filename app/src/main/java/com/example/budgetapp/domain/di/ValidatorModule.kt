package com.example.budgetapp.domain.di

import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.NumberValidator
import com.example.budgetapp.domain.usecase.validator.StringValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ValidatorModule {
    @Provides
    @Singleton
    fun provideRequiredStringValidator(): StringValidator {
        return StringValidator(required = true)
    }

    @Provides
    @Singleton
    fun provideRequiredNumberValidator(): NumberValidator {
        return NumberValidator(required = true)
    }

    @Provides
    @Singleton
    fun provideRequiredCurrencyValidator(): CurrencyValidator {
        return CurrencyValidator(required = true)
    }
}
