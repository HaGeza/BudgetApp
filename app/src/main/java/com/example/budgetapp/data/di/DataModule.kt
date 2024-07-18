package com.example.budgetapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.budgetapp.Constants.Companion.DATABASE_NAME
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.database.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }
}
