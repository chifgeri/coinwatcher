package com.xd4bhs.coinwatcher.data.network.swagger.client.api

import com.xd4bhs.coinwatcher.data.network.swagger.client.model.*
import retrofit2.Call
import retrofit2.http.*

interface CoinsApi {
    /**
     * Add new cryptourrency pair to the list. You have to add the currency and a vs currency
     *
     * @param currencyAddRequest Ticker of the coin
     * @return Call<CurrencyPairInfo>
    </Void> */
    @POST("coins/")
    fun coinsPost(
            @Body currencyAddRequest: CurrencyAddRequest?
    ): Call<CurrencyPairInfo?>?

    /**
     * List all supported coins price, market cap, volume, and market related data
     * Use this to obtain all the coins market data (price, market cap, volume)
     * @param vsCurrency The target currency of market data (usd, eur, jpy, etc.)
     * @param ids The ids of the coin, comma separated crytocurrency symbols (base). refers to `/coins/list`.\n&lt;b&gt;When left empty, returns numbers the coins observing the params `limit` and `start`&lt;/b&gt;
     * @param category filter by coin category, only decentralized_finance_defi and stablecoins are supported at the moment
     * @param order valid values: &lt;b&gt;market_cap_desc, gecko_desc, gecko_asc, market_cap_asc, market_cap_desc, volume_asc, volume_desc, id_asc, id_desc&lt;/b&gt;\nsort results by field.
     * @param perPage valid values: 1..250\n Total results per page
     * @param page Page through results
     * @param sparkline Include sparkline 7 days data (eg. true, false)
     * @param priceChangePercentage Include price change percentage in &lt;b&gt;1h, 24h, 7d, 14d, 30d, 200d, 1y&lt;/b&gt; (eg. &#39;`1h,24h,7d`&#39; comma-separated, invalid values will be discarded)
     * @return Call<List></List><CurrencyPairInfo>>
    </CurrencyPairInfo> */
    @GET("coins/markets")
    fun coinsMarketsGet(
            @Query("vs_currency") vsCurrency: String?, @Query("ids") ids: String?, @Query("category") category: String?, @Query("order") order: String?, @Query("per_page") perPage: Int?, @Query("page") page: Int?, @Query("sparkline") sparkline: Boolean?, @Query("price_change_percentage") priceChangePercentage: String?
    ): Call<List<CurrencyPairInfo?>?>?

    /**
     * Get current data (name, price, market, ... including exchange tickers) for a coin
     * Get current data (name, price, market, ... including exchange tickers) for a coin.&lt;br&gt;&lt;br&gt; **IMPORTANT**:\n Ticker object is limited to 100 items, to get more tickers, use `/coins/{id}/tickers`\n Ticker `is_stale` is true when ticker that has not been updated/unchanged from the exchange for a while.\n Ticker `is_anomaly` is true if ticker&#39;s price is outliered by our system.\n You are responsible for managing how you want to display these information (e.g. footnote, different background, change opacity, hide)
     * @param id pass the coin id (can be obtained from /coins) eg. bitcoin
     * @param localization Include all localized languages in response (true/false) &lt;b&gt;[default: true]&lt;/b&gt;
     * @param tickers Include tickers data (true/false) &lt;b&gt;[default: true]&lt;/b&gt;
     * @param marketData Include market_data (true/false) &lt;b&gt;[default: true]&lt;/b&gt;
     * @param communityData Include community_data data (true/false) &lt;b&gt;[default: true]&lt;/b&gt;
     * @param developerData Include developer_data data (true/false) &lt;b&gt;[default: true]&lt;/b&gt;
     * @param sparkline Include sparkline 7 days data (eg. true, false) &lt;b&gt;[default: false]&lt;/b&gt;
     * @return Call<InlineResponse200>
    </InlineResponse200> */
    @GET("coins/{id}")
    fun coinsIdGet(
            @Path("id") id: String?, @Query("localization") localization: String?, @Query("tickers") tickers: Boolean?, @Query("market_data") marketData: Boolean?, @Query("community_data") communityData: Boolean?, @Query("developer_data") developerData: Boolean?, @Query("sparkline") sparkline: Boolean?
    ): Call<InlineResponse200?>?

    /**
     * Edit the cryptourrency pair.
     *
     * @param id pass the coin id (can be obtained from /coins) eg. bitcoin
     * @param currencyEditRequest Ticker of the coin
     * @return Call<CurrencyPairInfo>
    </CurrencyPairInfo> */
    @PUT("coins/{id}")
    fun coinsIdPut(
            @Path("id") id: String?, @Body currencyEditRequest: CurrencyEditRequest?
    ): Call<CurrencyPairInfo?>?

    /**
     * Delete the cryptourrency pair.
     *
     * @param id pass the coin id (can be obtained from /coins) eg. bitcoin
     * @return Call<Void>
    </Void> */
    @DELETE("coins/{id}")
    fun coinsIdDelete(
            @Path("id") id: String?
    ): Call<Void?>?

    /**
     * Get historical market data include price, market cap, and 24h volume (granularity auto)
     * Get historical market data include price, market cap, and 24h volume (granularity auto)\n &lt;b&gt;Minutely data will be used for duration within 1 day, Hourly data will be used for duration between 1 day and 90 days, Daily data will be used for duration above 90 days.&lt;/b&gt;
     * @param id pass the coin id (can be obtained from /coins) eg. bitcoin
     * @param vsCurrency The target currency of market data (usd, eur, jpy, etc.)
     * @param days Data up to number of days ago (eg. 1,14,30,max)
     * @param interval Data interval. Possible value: daily
     * @return Call<CurrencyChartResponse>
    </CurrencyChartResponse> */
    @GET("coins/{id}/market_chart")
    fun coinsIdMarketChartGet(
            @Path("id") id: String?, @Query("vs_currency") vsCurrency: String?, @Query("days") days: String?, @Query("interval") interval: String?
    ): Call<CurrencyChartResponse?>?
}