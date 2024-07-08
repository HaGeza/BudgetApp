package com.example.budgetapp.di

import com.example.budgetapp.data.repository.LocalRepository
import com.example.budgetapp.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLocalRepository(repository: LocalRepository): Repository
}