package com.xd4bhs.coinwatcher.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

@Dao
interface CurrencyDAO{
    @Query("SELECT * FROM currency_pair")
    fun getAllCurrencies() : List<CurrencyPair>

    @Query("SELECT * FROM currency_pair WHERE currency_pair.id = :currPairId")
    fun getSpecificCurrencies(currPairId: String): List<CurrencyPair>

    @Insert
    fun insertCurrencies(vararg currencyPairs: CurrencyPair)
    
    @Delete
    fun deleteGrade(currencyPair: CurrencyPair)
}