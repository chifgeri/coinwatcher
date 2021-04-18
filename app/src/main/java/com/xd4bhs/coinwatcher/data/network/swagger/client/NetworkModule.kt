package com.xd4bhs.coinwatcher.data.network.swagger.client

import com.google.gson.GsonBuilder
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiClient(): Retrofit {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create()
        // TODO: read fro menvironment config
        var baseUrl = "https://api.coingecko.com/api/v3"
        if (!baseUrl.endsWith("/")) baseUrl = "$baseUrl/"
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient())
                .addConverterFactory(GsonCustomConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun provideCoinApi(client: Retrofit): CoinsApi {
        return client.create(CoinsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSimpleApi(client: Retrofit): SimpleApi {
        return client.create(SimpleApi::class.java)
    }
}