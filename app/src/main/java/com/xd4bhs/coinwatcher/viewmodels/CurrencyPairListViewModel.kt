package com.xd4bhs.coinwatcher.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.data.interactors.currencies.CurrenciesInteractor
import com.xd4bhs.coinwatcher.data.repositories.currencies.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyPairListViewModel : ViewModel() {
    @Inject
    lateinit var currencyInteractor: CurrenciesInteractor

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    val currencyPairList: MutableLiveData<ArrayList<CurrencyPair>> = MutableLiveData<ArrayList<CurrencyPair>>()
    val vsCurrencyList: MutableLiveData<List<String?>> = MutableLiveData<List<String?>>()

    fun queryVsCurrencyList(){
        val vsCurrencies = currencyInteractor.getVsCurrencies()
        vsCurrencyList.postValue(vsCurrencies)
    }

    fun queryCurrencyPairList(vs: String){
       val currs = currencyInteractor.listCryptoCurrecnciesByCurrency(currency = vs)
       currencyPairList.postValue(ArrayList(currs));
    }


}