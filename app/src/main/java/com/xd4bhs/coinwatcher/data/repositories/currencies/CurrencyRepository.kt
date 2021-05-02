package com.xd4bhs.coinwatcher.data.repositories.currencies

import android.content.Context
import com.xd4bhs.coinwatcher.data.database.AppDatabase
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

class CurrencyRepository(private val appDatabase: AppDatabase) {

    suspend fun queryCurrencies(): List<CurrencyPair>{

        return appDatabase.currencyDao().getAllCurrencies()
    }

    suspend fun getSpecificCurrency(currId: String): CurrencyPair{

        return appDatabase.currencyDao().getSpecificCurrency(currId)
    }

    suspend fun getSpecificCurrencyByVsCurr(vsCurr: String): List<CurrencyPair>{

        return appDatabase.currencyDao().getSpecificCurrencyByVsCurrency(vsCurr)
    }

    suspend fun saveCurrency(currencyPair: CurrencyPair){
        appDatabase.currencyDao().insertCurrencies(currencyPair)
    }

    suspend fun updateCurrency(currencyPair: CurrencyPair){
        appDatabase.currencyDao().updateCurrencyPair(currencyPair)
    }

    suspend fun deleteCurrency(currencyPair: CurrencyPair){
        appDatabase.currencyDao().deleteCurrency(currencyPair)
    }


}