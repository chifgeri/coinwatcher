package com.xd4bhs.coinwatcher.viewmodels.adapters

import android.app.Activity
import android.icu.util.Currency
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import com.xd4bhs.coinwatcher.views.format

class CurrencyRecyclerViewAdapter(var context: Activity, private var currencyPairArrayList: ArrayList<CurrencyPair>, val listener: CurrencyListListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currencyPair = currencyPairArrayList[position]
        val viewHolder = holder as RecyclerViewViewHolder
        viewHolder.tvName.text = "${currencyPair.ticker.toUpperCase()}/${currencyPair.vsCurrency.toUpperCase()}"
        viewHolder.tvPrice.text = "1 ${currencyPair.ticker.toString().toUpperCase()} = ${currencyPair.price.format(2)} ${currencyPair.vsCurrency.toUpperCase()}"


        holder.itemView.setOnClickListener {
            listener.onItemClick(currencyPairArrayList[position])
        }
    }

    override fun getItemCount(): Int {
        return currencyPairArrayList.size
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.pairName)
        var tvPrice: TextView = itemView.findViewById(R.id.textPrice)

    }

    interface CurrencyListListener {
       fun onItemClick(currencyPair: CurrencyPair)
    }

}