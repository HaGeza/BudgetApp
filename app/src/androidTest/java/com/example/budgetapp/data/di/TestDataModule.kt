package com.example.budgetapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestDataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }

    @Provides
    @Singleton
    fun provideExchangeRateDao(database: AppDatabase): ExchangeRateDao {
        return database.exchangeRateDao()
    }
}
