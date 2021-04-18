package com.xd4bhs.coinwatcher.views

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import com.xd4bhs.coinwatcher.data.repositories.currencies.CurrencyRepository
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class CurrencyListActivity : AppCompatActivity() {
    @Inject
    lateinit var currencyInteractor: CurrenciesInteractor

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thread {
            val vsCurrencies = currencyInteractor.getVsCurrencies()
            for (curr in vsCurrencies) {
                Log.d("NETWORK CALL: ", curr!!)
            }
        }

       lifecycleScope.launch {
            val curr = CurrencyPair(
                    id="btc",
                    vsCurrency = "EUR",
                    price = 100_000.0,
                    ticker = "BTC",
                    marketCap= 100_000_000_000.0,
                    totalVolume = 34001120.0,
            )
            currencyRepository.saveCurrency(ctx=this@CurrencyListActivity, currencyPair = curr)

          val currencies =  currencyRepository.queryCurrencies(this@CurrencyListActivity)
           for(curr in currencies) {
               Log.d("DATABASE: ", curr.ticker)
           }
        }
        setContentView(R.layout.activity_currency_list)
    }
}