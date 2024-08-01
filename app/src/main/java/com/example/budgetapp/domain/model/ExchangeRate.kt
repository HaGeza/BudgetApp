package com.example.budgetapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.budgetapp.Constants.Companion.EXCHANGE_RATE_TABLE
import java.util.Currency

/**
 * Model class for exchange rates, from `source` to `final` currency
 * @param source The source currency
 * @param other The final currency
 * @param rate The exchange rate
 * @param id The id of the exchange rate
 * */
@Entity(
    tableName = EXCHANGE_RATE_TABLE,
    indices = [Index(value = ["source", "other"], unique = true)]
)
@TypeConverters(Converters::class)
data class ExchangeRate(
    var source: Currency,
    var other: Currency,
    var rate: Double,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
)
