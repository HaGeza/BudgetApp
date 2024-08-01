package com.example.budgetapp.data.di

import com.example.budgetapp.data.repository.LocalAccountsRepository
import com.example.budgetapp.data.repository.LocalExchangeRatesRepository
import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.domain.repository.ExchangeRatesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** Module that provides the repository implementation */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /** Binds the [LocalAccountsRepository] to the [AccountsRepository] interface */
    @Binds
    abstract fun bindLocalAccountsRepo(repository: LocalAccountsRepository): AccountsRepository

    /** Binds the [LocalExchangeRatesRepository] to the [ExchangeRatesRepository] interface */
    @Binds
    abstract fun bindLocalExchangeRatesRepo(repository: LocalExchangeRatesRepository): ExchangeRatesRepository
}
