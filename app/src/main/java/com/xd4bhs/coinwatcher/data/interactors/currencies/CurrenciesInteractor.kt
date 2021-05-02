package com.xd4bhs.coinwatcher.data.interactors.currencies
import android.util.Log
import com.google.gson.internal.LinkedTreeMap
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.*
import javax.inject.Inject


class CurrenciesInteractor @Inject constructor(private var coinsApi: CoinsApi, private var simpleApi: SimpleApi) {



    private fun convertToCurrencyPair(vsCurrency: String, currencyPairInfo: CurrencyPairInfo): CurrencyPair{
        return CurrencyPair(
            id = currencyPairInfo.id!!,
            ticker = currencyPairInfo.symbol!!,
            vsCurrency = vsCurrency,
            price = currencyPairInfo.currentPrice!!,
            totalVolume = currencyPairInfo.totalVolume!!,
            marketCap = currencyPairInfo.marketCap!!
        )
    }

    private fun convertToCurrencyPair(vsCurrency: String, currencyPairData: CurrencyPairData): CurrencyPair{
        val priceMap = currencyPairData.marketData?.currentPrice!! as LinkedTreeMap<String, Double>
        val marketCapMap = currencyPairData.marketData?.marketCap!! as LinkedTreeMap<String, Double>
        val volumeMap = currencyPairData.marketData?.totalVolume!! as LinkedTreeMap<String, Double>
        return CurrencyPair(
                id = currencyPairData.id!!,
                ticker = currencyPairData.symbol!!,
                vsCurrency = vsCurrency,
                price = priceMap[vsCurrency]!!,
                totalVolume = volumeMap[vsCurrency]!! ,
                marketCap = marketCapMap[vsCurrency]!!
        )
    }

    fun getVsCurrencies(): List<String?> {
       val vsCurrencies = simpleApi.simpleSupportedVsCurrenciesGet()
       val response =  vsCurrencies?.execute()

        if(response?.code() == 200){
            return response.body()!!
        }

        throw  Error("Failed to fetch the currencies")
    }

    fun listCryptoCurrecnciesByCurrency(currency: String): List<CurrencyPair> {

        val coinList = coinsApi.coinsMarketsGet(currency, ids = null, category = null, order = null, perPage = 50, page = 0, sparkline = null, priceChangePercentage = null)

        val response =  coinList?.execute()

        if(response?.code() == 200){

          return response.body()!!.map {
              convertToCurrencyPair(currency, it!!)
          }
        }

        throw  Error("Failed to fetch the currency list")
    }

    // Its a big object with lot of keys
    // The app extract the data which needed in the view model

    fun getCurrencyByIdAndVsCurrency(vsCurrency: String, id: String): CurrencyPair {
        val coinDetail = coinsApi.coinsIdGet(id = id, localization = null, tickers = true, marketData = true, communityData = false,developerData = false, sparkline = false)

        val response =  coinDetail?.execute()

        if(response?.code() == 200){
            Log.d("NETWORK:", response.body()?.marketData!!.toString())
            return convertToCurrencyPair(vsCurrency = vsCurrency, currencyPairData  = response.body()!!)
        }

        throw  Error("Failed to fetch the currency detail")
    }

    fun editCurrency(id: String, editRequest: CurrencyEditRequest): CurrencyPair {
        val coinDetail = coinsApi.coinsIdPut(id, editRequest)

        val response =  coinDetail?.execute()

        if(response?.code() == 200){
            return convertToCurrencyPair(vsCurrency = "EUR",response.body()!!)
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

    fun addCurrencyPair(currencyAddRequest: CurrencyAddRequest, vsCurrency: String): CurrencyPair {
        val addCoin = coinsApi.coinsPost(currencyAddRequest)

        val response =  addCoin?.execute()

        if(response?.code() == 201){
            return convertToCurrencyPair(vsCurrency, response.body()!!)
        }

        throw  Error("Failed to fetch the currency detail")
    }

    fun getCoinChartData(id: String, vsCurrency: String, days: Int): CurrencyChartResponse {
        val getCoinChartData = coinsApi.coinsIdMarketChartGet(id = id, vsCurrency = vsCurrency, days = days.toString(), interval = "weekly" )

        try {
            val response =  getCoinChartData?.execute()


        if(response?.code() == 200){
            return response.body()!!
        }
        } catch(e: Throwable){
           Log.d("NETWORK", e.message!!)
        }


        throw  Error("Failed to fetch the currency chart data")
    }

}