package com.xd4bhs.coinwatcher.data.database.dao

import androidx.room.*
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

@Dao
interface CurrencyDAO{
    @Query("SELECT * FROM currency_pair")
    suspend fun getAllCurrencies() : List<CurrencyPair>

    @Query("SELECT * FROM currency_pair WHERE currency_pair.id = :currPairId")
    suspend fun getSpecificCurrency(currPairId: String): CurrencyPair

    @Query("SELECT * FROM currency_pair WHERE currency_pair.vsCurrency = :vsCurrName")
    suspend fun getSpecificCurrencyByVsCurrency(vsCurrName: String): List<CurrencyPair>


    @Update
    suspend fun updateCurrencyPair(currencyPair: CurrencyPair)

    @Insert
    suspend fun insertCurrencies(vararg currencyPairs: CurrencyPair)

    @Delete
    suspend fun deleteCurrency(currencyPair: CurrencyPair)
}