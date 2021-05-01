package com.xd4bhs.coinwatcher.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.viewmodels.CurrencyPairListViewModel
import com.xd4bhs.coinwatcher.viewmodels.CurrencyPairViewModel
import dagger.hilt.android.AndroidEntryPoint

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@AndroidEntryPoint
class CurrencyPairDetailActivity : AppCompatActivity() {
    lateinit var currencyViewModel: CurrencyPairViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_pair_detail)
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyPairViewModel::class.java)

        val price: TextView = findViewById(R.id.tvPriceDetail)
        val marketCap: TextView = findViewById(R.id.tvMarketCapDetail)
        val volume: TextView = findViewById(R.id.tvTradingVolume)
        val name: TextView = findViewById(R.id.tvDetailName)

        val delete: Button = findViewById(R.id.deleteButton)

        delete.setOnClickListener {
            currencyViewModel.deleteCurrency(ctx = this)
        }


        val id = intent.getStringExtra(COIN_ID)
        val vsCurr = intent.getStringExtra(VS_CURR)

        currencyViewModel.currencyPair.observe(this, {
            if(it != null){
                price.text = "${it.price.format(2)} ${it.vsCurrency}"
                marketCap.text = "${(it.marketCap/1_000_000).format(2)} M"
                volume.text = "${(it.totalVolume/1_000_000).format(2)} M"
                name.text = "${it.ticker}/${it.vsCurrency.toUpperCase()}"
            }
        })

        currencyViewModel.getCurrency(ctx = this, id = id!!, vsCurr = vsCurr!!)

    }
}