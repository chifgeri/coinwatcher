package com.xd4bhs.coinwatcher.data.repositories

import android.content.Context
import com.xd4bhs.coinwatcher.data.database.AppDatabase
import com.xd4bhs.coinwatcher.data.repositories.currencies.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrenciesRepository(@ApplicationContext context: Context): CurrencyRepository {
        return CurrencyRepository(AppDatabase.getInstance(context))
    }
}