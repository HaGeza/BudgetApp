package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetapp.domain.model.ExchangeRate
import com.example.budgetapp.Constants.Companion.BASE_CURRENCY_CODE
import kotlinx.coroutines.flow.Flow

/** Data Access Objecct for the exchange rates table */
@Dao
interface ExchangeRateDao : BaseDao<ExchangeRate> {
    /** Get all exchange rates */
    @Query("SELECT * FROM exchange_rates")
    override fun getAll(): Flow<List<ExchangeRate>>

    /**
     * Get an exchange rate by id
     * @param id The id of the exchange rate to get
     */
    @Query("SELECT * FROM exchange_rates WHERE id = :id")
    override fun getById(id: Int): Flow<ExchangeRate?>

    /**
     * Get an exchange rate from `source` to currency with [BASE_CURRENCY_CODE]
     * @param source The source currency of the exchange rate to get
     */
    @Query("SELECT * FROM exchange_rates WHERE source = :source")
    fun getBySource(source: String): Flow<ExchangeRate?>

    @Insert
    suspend fun insertAll(exchangeRates: List<ExchangeRate>)
}
