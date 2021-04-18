package com.xd4bhs.coinwatcher.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xd4bhs.coinwatcher.data.database.dao.CurrencyDAO
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

@Database(entities = [CurrencyPair::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currencyDao(): CurrencyDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "CoinWatcherDataBase"
                )
                 .fallbackToDestructiveMigration()
                  .build()
                  .also { INSTANCE = it }
            }
        }
    }
}