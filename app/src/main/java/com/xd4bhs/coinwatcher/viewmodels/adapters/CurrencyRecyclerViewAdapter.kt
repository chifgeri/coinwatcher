package com.xd4bhs.coinwatcher.viewmodels.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair

class CurrencyRecyclerViewAdapter(context: Activity, currencyPairArrayList: ArrayList<CurrencyPair>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    var context: Activity = context
    var currencyPairArrayList: ArrayList<CurrencyPair> = currencyPairArrayList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currencyPair = currencyPairArrayList[position]
        val viewHolder = holder as RecyclerViewViewHolder
        Log.d("ADAPTER", "Called")
        viewHolder.tvName.text = "${currencyPair.ticker}/${currencyPair.vsCurrency}"
        viewHolder.tvPrice.text = currencyPair.price.toString()
    }

    override fun getItemCount(): Int {
        return currencyPairArrayList.size
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.pairName)
        var tvPrice: TextView = itemView.findViewById(R.id.textPrice)

    }

}