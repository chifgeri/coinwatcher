package com.xd4bhs.coinwatcher

import android.content.Context
import com.xd4bhs.coinwatcher.data.database.AppDatabase
import com.xd4bhs.coinwatcher.data.repositories.currencies.CurrencyRepository
import com.xd4bhs.coinwatcher.mock.createMockDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

class TestCurrencyRepository {
    lateinit var currencyRepository: CurrencyRepository

    @Before
    fun setup(){
        currencyRepository = CurrencyRepository(createMockDatabase())
    }

    @Test
    fun queryCurrencies(){
        GlobalScope.launch {
            val list = currencyRepository.queryCurrencies()
            assert(list[0].id == "myCurrencyPair")
        }
    }

    @Test
    fun queryCurrencyById(){
        GlobalScope.launch {
            val value = currencyRepository.getSpecificCurrency("myCurrencyPair")
            assert(value.id == "myCurrencyPair")
        }
    }

    @Test
    fun queryCurrencyByVsCurrency(){
        GlobalScope.launch {
            val list = currencyRepository.getSpecificCurrencyByVsCurr("usd")
            assert(list[0].id == "myCurrencyPair")
        }
    }

    @Test
    fun deleteCurrencies(){
        GlobalScope.launch {
            val value = currencyRepository.getSpecificCurrency("myCurrencyPair")
            currencyRepository.deleteCurrency(value)
        }
    }
}