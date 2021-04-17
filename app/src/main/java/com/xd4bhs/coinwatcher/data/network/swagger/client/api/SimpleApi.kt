package com.xd4bhs.coinwatcher.data.network.swagger.client.api

import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {
    /**
     * Get list of supported_vs_currencies.
     *
     * @return Call<List></List><String>>
    </String> */
    @GET("simple/supported_vs_currencies")
    fun simpleSupportedVsCurrenciesGet(): Call<List<String?>?>?
}