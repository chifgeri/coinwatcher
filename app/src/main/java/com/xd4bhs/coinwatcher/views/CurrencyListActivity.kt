package com.xd4bhs.coinwatcher.views

import RecyclerViewAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.viewmodels.CurrencyPairListViewModel

class CurrencyListActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var currencyListViewModel: CurrencyPairListViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        recyclerView = findViewById(R.id.rvList);

        currencyListViewModel = ViewModelProviders.of(this).get(CurrencyPairListViewModel::class.java)

        currencyListViewModel.currencyPairList.observe(this, Observer { list ->
            recyclerViewAdapter = RecyclerViewAdapter(this, list)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
        })
    }
}