package com.example.budgetapp.domain.di

import com.example.budgetapp.domain.usecase.validator.CurrencyValidator
import com.example.budgetapp.domain.usecase.validator.NumberValidator
import com.example.budgetapp.domain.usecase.validator.StringValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Module that provides validators for the application */
@Module
@InstallIn(SingletonComponent::class)
class ValidatorModule {
    /** Provides a [StringValidator] with required set to `true` */
    @Provides
    @Singleton
    fun provideRequiredStringValidator(): StringValidator {
        return StringValidator(required = true)
    }

    /** Provides a [NumberValidator] with required set to `true` */
    @Provides
    @Singleton
    fun provideRequiredNumberValidator(): NumberValidator {
        return NumberValidator(required = true)
    }

    /** Provides a [CurrencyValidator] with required set to `true` */
    @Provides
    @Singleton
    fun provideRequiredCurrencyValidator(): CurrencyValidator {
        return CurrencyValidator(required = true)
    }
}
