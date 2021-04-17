package com.xd4bhs.coinwatcher.data.repositories

import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrenciesRepository(coinsApi: CoinsApi, simpleApi: SimpleApi): CurrenciesInteractor {
        return CurrenciesInteractor(coinsApi, simpleApi)
    }
}