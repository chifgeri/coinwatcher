package com.xd4bhs.coinwatcher.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyListActivity : AppCompatActivity() {
    @Inject
    lateinit var currencyInteractor: CurrenciesInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currencyInteractor.getVsCurrencies()

        setContentView(R.layout.activity_currency_list)
    }
}