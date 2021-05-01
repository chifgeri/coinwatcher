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
import javax.inject.Inject

@HiltViewModel
class CurrencyPairViewModel  @Inject constructor(var currencyInteractor: CurrenciesInteractor, var currencyRepository: CurrencyRepository) : ViewModel(){
        val currencyPair: MutableLiveData<CurrencyPair?> = MutableLiveData()

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

}