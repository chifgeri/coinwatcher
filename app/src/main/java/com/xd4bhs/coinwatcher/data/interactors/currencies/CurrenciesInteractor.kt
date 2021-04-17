package com.xd4bhs.coinwatcher.data.interactors.currencies

import android.util.Log
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.CoinsApi
import com.xd4bhs.coinwatcher.data.network.swagger.client.api.SimpleApi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(private var coinsApi: CoinsApi, private var simpleApi: SimpleApi) {

    fun getVsCurrencies(){
        var vsCurrencies = simpleApi.simpleSupportedVsCurrenciesGet()

        vsCurrencies?.enqueue(
                object : Callback<List<String?>?> {
                    override fun onResponse(call: Call<List<String?>?>, response: Response<List<String?>?>) {
                        if (response.code() == 200) {
                            val response = response.body()!!

                            for(str in response){
                                Log.d("TAG", str!!)
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<String?>?>, t: Throwable) {
                        // TODO
                    }
                }
        )
    }
  fun listCurrecnciesByCurrency(){
        coinsApi.coinsMarketsGet("eur", null, null, null, null, null, null, null)
    }


}