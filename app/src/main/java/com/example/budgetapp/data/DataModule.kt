package com.example.budgetapp.data

import android.content.Context
import androidx.room.Room
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
    fun provideAccountDatabase(@ApplicationContext context: Context): AccountDatabase {
        return Room.databaseBuilder(
            context,
            AccountDatabase::class.java,
            "account_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountDao(database: AccountDatabase): AccountDao {
        return database.accountDao()
    }
}