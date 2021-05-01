package com.xd4bhs.coinwatcher.viewmodels

import android.content.Context
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.AdapterView
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
class CurrencyPairListViewModel @Inject constructor(var currencyInteractor: CurrenciesInteractor, var currencyRepository: CurrencyRepository) :
    ViewModel(), AdapterView.OnItemSelectedListener {

    val currencyPairList: MutableLiveData<ArrayList<CurrencyPair>> = MutableLiveData<ArrayList<CurrencyPair>>()
    val vsCurrencyList: MutableLiveData<List<String?>> = MutableLiveData<List<String?>>()
    val selectedVsCurrency: MutableLiveData<String?> = MutableLiveData<String?>()

    fun queryVsCurrencyList(){
        viewModelScope.launch(Dispatchers.IO) {
            val vsCurrencies = currencyInteractor.getVsCurrencies()
            vsCurrencyList.postValue(vsCurrencies)
        }
    }

    fun queryCurrencyPairList(ctx: Context, vs: String){
        viewModelScope.launch(Dispatchers.IO) {
            val currs = currencyInteractor.listCryptoCurrecnciesByCurrency(currency = vs)
            val currs2 = currencyRepository.getSpecificCurrencyByVsCurr(vsCurr = vs, ctx = ctx)
            val array = ArrayList(currs2)
            array.addAll(currs)
            currencyPairList.postValue(array)
        }
    }

    fun addCurrency(ctx: Context, currency: CurrencyPair){
        viewModelScope.launch(Dispatchers.IO) {
            currencyRepository.saveCurrency(ctx=ctx, currencyPair = currency)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedVsCurrency.value = vsCurrencyList.value?.get(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedVsCurrency.value = null
    }


}