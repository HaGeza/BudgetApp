package com.example.budgetapp.domain.model

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Currency

/** Class for handling conversions required for the Room database */
class Converters {
    /**
     * Convert [BigDecimal] to [String]
     * @param value - value to be converted
     * @return String - converted value
     * */
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    /**
     * Convert [String] to [BigDecimal]. May throw [NumberFormatException]
     * @param value - value to be converted
     * @return BigDecimal - converted value
     */
    @TypeConverter
    @Throws(NumberFormatException::class)
    fun toBigDecimal(value: String): BigDecimal {
        return BigDecimal(value)
    }

    /**
     * Convert [Currency] to [String]
     * @param value: Currency - value to be converted
     * @return String - converted value
     */
    @TypeConverter
    fun fromCurrency(value: Currency): String {
        return value.currencyCode
    }

    /**
     * Convert [String] to [Currency]. May throw [IllegalArgumentException] if the currency code is
     * is invalid or [NullPointerException] if the currency code is null
     * @param value - value to be converted
     * @return Currency - converted value
     */
    @TypeConverter
    @kotlin.jvm.Throws(IllegalArgumentException::class, NullPointerException::class)
    fun toCurrency(value: String): Currency {
        return Currency.getInstance(value)
    }
}
