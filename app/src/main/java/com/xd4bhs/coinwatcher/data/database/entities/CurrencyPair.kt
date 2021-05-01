package com.xd4bhs.coinwatcher.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Example Class

@Entity(tableName = "currency_pair")
data class CurrencyPair(
    @PrimaryKey val id: String,
            val vsCurrency: String,
            val price: Double,
            val ticker: String,
            val marketCap: Double,
            val totalVolume: Double,

)
