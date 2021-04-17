package com.xd4bhs.coinwatcher.data.interactors.currencies

import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.CurrencyAddRequest
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.CurrencyChartResponse
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.CurrencyEditRequest
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.CurrencyPairInfo
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(private var coinsApi: CoinsApi, private var simpleApi: SimpleApi) {

    fun getVsCurrencies(): List<String?> {
       val vsCurrencies = simpleApi.simpleSupportedVsCurrenciesGet()
       val response =  vsCurrencies?.execute()

        if(response?.code() == 200){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currencies")
    }

    fun listCryptoCurrecnciesByCurrency(currency: String): List<CurrencyPairInfo?> {
        val coinList = coinsApi.coinsMarketsGet(currency, ids = null, category = null, order = null, perPage = 50, page = 0, sparkline = null, priceChangePercentage = null)

        val response =  coinList?.execute()

        if(response?.code() == 200){
          return response.body()!!
        }

        throw  Error("Failed to fetch the currency list")
    }

    // Its a big object with lot of keys
    // The app extract the data which needed in the view model
    fun getCurrencyById(id: String): Any {
        val coinDetail = coinsApi.coinsIdGet(id = id, localization = null, tickers = true, marketData = true, communityData = false,developerData = false, sparkline = false)

        val response =  coinDetail?.execute()

        if(response?.code() == 200){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currency detail")
    }

    fun editCurrency(id: String, editRequest: CurrencyEditRequest): CurrencyPairInfo {
        val coinDetail = coinsApi.coinsIdPut(id, editRequest)

        val response =  coinDetail?.execute()

        if(response?.code() == 200){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currency list")
    }

    fun deleteCurrency(id: String): Boolean {
        val coinDetail = coinsApi.coinsIdDelete(id)

        val response =  coinDetail?.execute()

        if(response?.code() == 204){
            return true
        }
        return false
    }

    fun addCurrencyPair(currencyAddRequest: CurrencyAddRequest): CurrencyPairInfo {
        val addCoin = coinsApi.coinsPost(currencyAddRequest)

        val response =  addCoin?.execute()

        if(response?.code() == 201){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currency detail")
    }

    fun getCoinChartData(id: String, vsCurrency: String, days: Int): CurrencyChartResponse {
        val getCoinChartData = coinsApi.coinsIdMarketChartGet(id = id, vsCurrency = vsCurrency, days = days.toString(), interval = null )

        val response =  getCoinChartData?.execute()

        if(response?.code() == 201){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currency detail")
    }

}