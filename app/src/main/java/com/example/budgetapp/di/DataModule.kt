package com.example.budgetapp.di

import android.content.Context
import androidx.room.Room
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAccountDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "account_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountDao(database: Database): AccountDao {
        return database.accountDao()
    }
}