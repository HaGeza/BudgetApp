package com.example.budgetapp.domain.di

import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.NonEmptyStringValidator
import com.example.budgetapp.domain.usecase.validator.NumberValidator
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
    fun provideNonEmptyStringValidator(): NonEmptyStringValidator {
        return NonEmptyStringValidator(required = true)
    }

    @Provides
    @Singleton
    fun provideNumberValidator(): NumberValidator {
        return NumberValidator(required = true)
    }

    @Provides
    @Singleton
    fun provideCurrencyValidator(): CurrencyValidator {
        return CurrencyValidator(required = true)
    }
}
