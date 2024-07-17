package com.example.budgetapp.domain.model;

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.math.BigDecimal
import java.util.Currency

@Entity(tableName = "accounts")
@TypeConverters(Converters::class)
data class Account(
    var name: String,
    var balance: BigDecimal,
    var currency: Currency,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
);
