package com.example.budgetapp.data.di

import com.example.budgetapp.domain.repository.AccountsRepository
import com.example.budgetapp.data.repository.LocalAccountsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLocalAccountRepo(repository: LocalAccountsRepository): AccountsRepository
}