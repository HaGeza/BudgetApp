package com.example.budgetapp.data

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Currency

class Converters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
    }

    @TypeConverter
    fun fromCurrency(value: Currency): String {
        return value.currencyCode
    }

    @TypeConverter
    fun toCurrency(value: String): Currency {
        return Currency.getInstance(value)
    }
}