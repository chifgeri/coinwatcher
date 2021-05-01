package com.xd4bhs.coinwatcher.views

import android.content.Intent
import com.xd4bhs.coinwatcher.viewmodels.adapters.CurrencyRecyclerViewAdapter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.viewmodels.CurrencyPairListViewModel
import dagger.hilt.android.AndroidEntryPoint

const val COIN_ID = "COIN_ID"

const val VS_CURR = "VS_CURR"


@AndroidEntryPoint
class CurrencyListActivity : AppCompatActivity(), CoinPairDialogFragment.CoinPairDialogListener, CurrencyRecyclerViewAdapter.CurrencyListListener {

    private var recyclerViewAdapter: CurrencyRecyclerViewAdapter? = null
    lateinit var currencyListViewModel: CurrencyPairListViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)
        currencyListViewModel = ViewModelProviders.of(this).get(CurrencyPairListViewModel::class.java)


        recyclerView = findViewById(R.id.rvList)
        val button: Button = findViewById(R.id.addButton)

        button.setOnClickListener {
            showAddFragment()
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        
        spinner.onItemSelectedListener = currencyListViewModel

        currencyListViewModel.queryVsCurrencyList()

        currencyListViewModel.currencyPairList.observe(this, { list ->
            recyclerViewAdapter = CurrencyRecyclerViewAdapter(this, list, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.adapter?.notifyDataSetChanged()
        })

        currencyListViewModel.vsCurrencyList.observe(this,  {
            list ->
            spinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list)
        })

        currencyListViewModel.selectedVsCurrency.observe(this,  {
            if(it != null){
                currencyListViewModel.queryCurrencyPairList(ctx=this , vs = it)
            }
        })
    }

    private fun showAddFragment(){
        val newFragment = CoinPairDialogFragment(positiveIconText = "Add", title = "Add new coin pair")
        newFragment.show(supportFragmentManager, "coinPairAdd")

    }

    override fun onDialogPositiveClick(dialog: DialogFragment, coinPair: CurrencyPair) {
        currencyListViewModel.addCurrency(this, coinPair)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    override fun onItemClick(currencyPair: CurrencyPair) {
        val intent = Intent(this, CurrencyPairDetailActivity::class.java).apply {
            putExtra(COIN_ID, currencyPair.id!!)
            putExtra(VS_CURR, currencyPair.vsCurrency)
        }
        startActivity(intent)

    }

}