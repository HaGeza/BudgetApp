package com.example.budgetapp.domain.model;

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.budgetapp.Constants.Companion.ACCOUNTS_TABLE
import java.math.BigDecimal
import java.util.Currency

/**
 * Model class for Account
 * @param name: String - name of the account
 * @param balance: BigDecimal - balance of the account
 * @param currency: Currency - currency of the account
 * @param id: Int - id of the account - primary key, auto-generated, default value is 0
 * */
@Entity(tableName = ACCOUNTS_TABLE)
@TypeConverters(Converters::class)
data class Account(
    var name: String,
    var balance: BigDecimal,
    var currency: Currency,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
);
