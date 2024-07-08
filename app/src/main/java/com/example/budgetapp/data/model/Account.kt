package com.example.budgetapp.data.model;

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.math.BigDecimal
import java.util.Currency

@Entity(tableName = "accounts")
@TypeConverters(Converters::class)
data class Account(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var balance: BigDecimal,
    var currency: Currency
);
