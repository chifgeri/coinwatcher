package com.xd4bhs.coinwatcher

import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import com.xd4bhs.coinwatcher.mock.MockCoinsApi
import com.xd4bhs.coinwatcher.mock.MockSimpleApi
import org.junit.Before
import org.junit.Test
import java.lang.NullPointerException

class TestCurrencyInteractor {
     lateinit var interactor: CurrenciesInteractor

    @Before
    fun setup(){
        interactor = CurrenciesInteractor(coinsApi = MockCoinsApi(), simpleApi =  MockSimpleApi())
    }

    @Test
    fun getCurrencyPairListTestSuccess(){
        val list = interactor.listCryptoCurrecnciesByCurrency("btc")

        assert(list.size == 1)
        assert(list[0].id == "btc")
        assert(list[0].price == 100.0)
    }

    @Test(expected = Error::class)
    fun getCurrencyPairListTestError(){
         interactor.listCryptoCurrecnciesByCurrency("ada")
    }

    @Test
    fun getVsCurrenciesSuccess(){
        val list = interactor.getVsCurrencies()

        assert(list.size == 3)
        assert(list.contains("usd"))
        assert(list.contains("btc"))
        assert(list.contains("huf"))
    }

    @Test
    fun getCurrencyDetailSuccess(){
        val value = interactor.getCurrencyByIdAndVsCurrency(id = "btc", vsCurrency = "usd")

        assert(value.id=="btc")
        assert(value.vsCurrency == "usd")
        assert(value.marketCap == 1000.0)
        assert(value.price == 1000.0)
        assert(value.totalVolume == 1000.0)


    }

    // Should throw error because mock data marketData map property does not have "ada" keys
    @Test(expected = NullPointerException::class)
    fun getCurrencyDetailError(){
        interactor.getCurrencyByIdAndVsCurrency(id = "btc", vsCurrency = "ada")
    }

    @Test
    fun deleteCurrency(){
        val response = interactor.deleteCurrency("btc")
        assert(response)
    }


}