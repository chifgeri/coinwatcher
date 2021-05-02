package com.xd4bhs.coinwatcher.viewmodels

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import com.xd4bhs.coinwatcher.data.repositories.currencies.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrencyPairViewModel  @Inject constructor(var currencyInteractor: CurrenciesInteractor, var currencyRepository: CurrencyRepository) : ViewModel(){
        val currencyPair: MutableLiveData<CurrencyPair?> = MutableLiveData()
        val chartData: MutableLiveData<List<Pair<Double, Double>>?> = MutableLiveData()

        fun deleteCurrency(ctx: Context){
                if(currencyPair.value != null && currencyPair.value?.id != null) {
                        viewModelScope.launch(Dispatchers.IO) {
                                currencyRepository.deleteCurrency(ctx = ctx, currencyPair = currencyPair.value!!)
                                currencyPair.postValue(null)
                                val act = ctx as Activity
                                act.finish()
                        }
                }
        }

        fun getCurrency(ctx: Context, id: String, vsCurr: String){
                viewModelScope.launch(Dispatchers.IO) {
                           try {
                              val coin =  currencyInteractor.getCurrencyByIdAndVsCurrency(vsCurrency = vsCurr, id = id)
                               Log.d("NETWORK: ", coin.id)


                              currencyPair.postValue(coin)
                           } catch (err: Throwable) {
                               val coin = currencyRepository.getSpecificCurrency(currId = id, ctx=ctx)
                               currencyPair.postValue(coin)
                          }
                 }
        }

        fun getCurrencyChartData( id: String, vsCurr: String){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val data = currencyInteractor.getCoinChartData(id = id, vsCurrency = vsCurr, days = 3)
                    val values: List<Pair<Double, Double>> = data.prices.map {
                        val dateTime = it[0]
                        val price = it[1]
                        Pair(dateTime, price)
                    }
                    chartData.postValue(values)
                } catch (err: Error){
                    val values: ArrayList<Pair<Double, Double>> = ArrayList()
                    values.add(Pair(1.0, 22.0))
                    values.add(Pair(2.0, 44.0))
                    values.add(Pair(3.0, 55.0))
                    values.add(Pair(4.0, 66.0))
                    values.add(Pair(5.0, 11.0))

                    chartData.postValue(values)
                }
            }
        }

}