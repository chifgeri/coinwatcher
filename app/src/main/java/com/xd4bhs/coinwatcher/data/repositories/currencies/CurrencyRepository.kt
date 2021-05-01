package com.xd4bhs.coinwatcher.data.repositories.currencies

import android.content.Context
import com.xd4bhs.coinwatcher.data.database.AppDatabase
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

class CurrencyRepository {

    suspend fun queryCurrencies(ctx: Context): List<CurrencyPair>{

        return AppDatabase.getInstance(ctx).currencyDao().getAllCurrencies()
    }

    suspend fun getSpecificCurrency(currId: String, ctx: Context): CurrencyPair{

        return AppDatabase.getInstance(ctx).currencyDao().getSpecificCurrency(currId)
    }

    suspend fun getSpecificCurrencyByVsCurr(vsCurr: String, ctx: Context): List<CurrencyPair>{

        return AppDatabase.getInstance(ctx).currencyDao().getSpecificCurrencyByVsCurrency(vsCurr)
    }

    suspend fun saveCurrency(currencyPair: CurrencyPair,ctx: Context){
        AppDatabase.getInstance(ctx).currencyDao().insertCurrencies(currencyPair)
    }

    suspend fun updateCurrency(currencyPair: CurrencyPair,ctx: Context){
        AppDatabase.getInstance(ctx).currencyDao().updateCurrencyPair(currencyPair)
    }

    suspend fun deleteCurrency(ctx: Context, currencyPair: CurrencyPair){
        AppDatabase.getInstance(ctx).currencyDao().deleteCurrency(currencyPair)
    }


}