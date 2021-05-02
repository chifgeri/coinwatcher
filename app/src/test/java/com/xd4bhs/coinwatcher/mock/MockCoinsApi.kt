package com.xd4bhs.coinwatcher.mock

import com.google.gson.internal.LinkedTreeMap
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.model.*
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MockCoinsApi: CoinsApi {
    val currenciesInfoMock: ArrayList<CurrencyPairInfo?> = ArrayList()
    val currenciesDataMock: ArrayList<CurrencyPairData?> = ArrayList()

    init {
        val curr = CurrencyPairInfo()
        curr.id = "btc"
        curr.symbol = "btc"
        curr.currentPrice = 100.0
        curr.marketCap = 1_000_000.0
        curr.totalVolume = 1_000_000.0
        curr.name="btc"
        currenciesInfoMock.add(curr)

        val marketData = CurrencyPairDataMarketData()
        val price = LinkedTreeMap<String, Double>()
        price["usd"] = 1000.0

        marketData.currentPrice = price
        marketData.marketCap = price
        marketData.totalVolume = price
        val curr2 = CurrencyPairData()
        curr2.id = "btc"
        curr2.symbol = "btc"
        curr2.marketData = marketData

        currenciesDataMock.add(curr2)
    }

    override fun coinsPost(currencyAddRequest: CurrencyAddRequest?): Call<CurrencyPairInfo?>? {
        val call = object: Call<CurrencyPairInfo?> {
            override fun clone(): Call<CurrencyPairInfo?> {
                return this
            }

            override fun execute(): Response<CurrencyPairInfo?> {
                return Response.success(currenciesInfoMock[0])
            }

            override fun enqueue(callback: Callback<CurrencyPairInfo?>) {
            }

            override fun isExecuted(): Boolean {
              return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
               return null
            }

        }
        return call
    }

    override fun coinsMarketsGet(vsCurrency: String?, ids: String?, category: String?, order: String?, perPage: Int?, page: Int?, sparkline: Boolean?, priceChangePercentage: String?): Call<List<CurrencyPairInfo?>?>? {
        val call = object: Call<List<CurrencyPairInfo?>?> {
            override fun clone(): Call<List<CurrencyPairInfo?>?> {
                return this
            }

            override fun execute(): Response<List<CurrencyPairInfo?>?> {
                val result = currenciesInfoMock.filter {
                    it?.id.equals(vsCurrency)
                }
                if(result.isEmpty()){
                    return Response.error(404, ResponseBody.create(MediaType.parse("application/json"), "null"))
                }
                return Response.success(result)
            }

            override fun enqueue(callback: Callback<List<CurrencyPairInfo?>?>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
                return null
            }

        }
        return call
    }

    override fun coinsIdGet(id: String?, localization: String?, tickers: Boolean?, marketData: Boolean?, communityData: Boolean?, developerData: Boolean?, sparkline: Boolean?): Call<CurrencyPairData?>? {
        val call = object: Call<CurrencyPairData?> {
            override fun clone(): Call<CurrencyPairData?> {
                return this
            }

            override fun execute(): Response<CurrencyPairData?> {
                return Response.success(currenciesDataMock[0])
            }

            override fun enqueue(callback: Callback<CurrencyPairData?>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
                return null
            }

        }
        return call
    }

    override fun coinsIdPut(id: String?, currencyEditRequest: CurrencyEditRequest?): Call<CurrencyPairInfo?>? {
        val call = object: Call<CurrencyPairInfo?> {
            override fun clone(): Call<CurrencyPairInfo?> {
                return this
            }

            override fun execute(): Response<CurrencyPairInfo?> {
                return Response.success(currenciesInfoMock[0])
            }

            override fun enqueue(callback: Callback<CurrencyPairInfo?>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
                return null
            }

        }
        return call
    }

    override fun coinsIdDelete(id: String?): Call<Void?>? {
        val call = object: Call<Void?> {
            override fun clone(): Call<Void?> {
                return this
            }

            override fun execute(): Response<Void?> {
                return Response.success( null)
            }

            override fun enqueue(callback: Callback<Void?>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
                return null
            }

        }
        return call
    }

    override fun coinsIdMarketChartGet(id: String?, vsCurrency: String?, days: String?, interval: String?): Call<CurrencyChartResponse?>? {
        TODO("Not yet implemented")
    }
}

class MockSimpleApi: SimpleApi {
    override fun simpleSupportedVsCurrenciesGet(): Call<List<String?>?>? {
        val call = object: Call<List<String?>?> {
            override fun clone(): Call<List<String?>?> {
                return this
            }

            override fun execute(): Response<List<String?>?> {
                val strings = ArrayList<String>()
                strings.add("btc")
                strings.add("usd")
                strings.add("huf")
                return Response.success(strings)
            }

            override fun enqueue(callback: Callback<List<String?>?>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request? {
                return null
            }

        }
        return call
    }

}