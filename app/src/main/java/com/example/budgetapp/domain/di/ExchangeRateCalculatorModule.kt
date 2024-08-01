package com.example.budgetapp.domain.di

import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.domain.usecase.ExchangeRateCalculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Module that provides the [ExchangeRateCalculator] for the application */
@Module
@InstallIn(SingletonComponent::class)
class ExchangeRateCalculatorModule {
    @Provides
    @Singleton
    fun provideExchangeRateCalculator(exchangeRateDao: ExchangeRateDao): ExchangeRateCalculator {
        return ExchangeRateCalculator(exchangeRateDao)
    }
}
