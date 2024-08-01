package com.example.budgetapp.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.budgetapp.Constants.Companion.DATABASE_NAME
import com.example.budgetapp.Constants.Companion.DATABASE_VERSION
import com.example.budgetapp.data.dao.AccountDao
import com.example.budgetapp.data.dao.ExchangeRateDao
import com.example.budgetapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import javax.inject.Singleton

/** Module that provides the database and dao instances */
@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    /**
     * Provides the database instance
     * @param context the application context
     * @return the database instance
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val databaseFile = context.getDatabasePath(DATABASE_NAME)
        val builder = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )

        if (!databaseFile.exists()) {
            val assetPath = "database/AppDatabase_${DATABASE_VERSION}.db"
            try {
                context.assets.open(assetPath).use {
                    builder.createFromAsset(assetPath)
                }
            } catch (e: IOException) {
                Log.w("DataModule", "Asset file does not exist: $assetPath")
            }
        }

        return builder.build()
    }

    /**
     * Provides the [AccountDao] instance
     * @param database the database instance
     * @return the [AccountDao] instance
     */
    @Provides
    @Singleton
    fun provideAccountDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }

    /**
     * Provides the [ExchangeRateDao] instance
     * @param database the database instance
     * @param the [ExchangeRateDao] instance
     */
    @Provides
    @Singleton
    fun provideExchangeRateDao(database: AppDatabase): ExchangeRateDao {
        return database.exchangeRateDao()
    }
}
