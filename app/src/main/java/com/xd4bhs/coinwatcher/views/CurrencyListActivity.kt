package com.xd4bhs.coinwatcher.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import retrofit2.Retrofit


class CurrencyListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
                .baseUrl("api.coingecko.com/api/v3")
                .build()

        val service =  retrofit.create(CoinsApi::class.java)

        val response = service.coinsMarketsGet("eur", null, null, null , null, null, null, null )

        for(value in response.){
            Log.d("TAG", value)
        }


        setContentView(R.layout.activity_currency_list)
    }
}