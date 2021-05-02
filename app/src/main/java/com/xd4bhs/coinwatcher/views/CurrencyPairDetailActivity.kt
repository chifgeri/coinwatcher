package com.xd4bhs.coinwatcher.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.xd4bhs.coinwatcher.R
import com.xd4bhs.coinwatcher.viewmodels.CurrencyPairViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


fun Double.format(digits: Int) = "%.${digits}f".format(this)

@AndroidEntryPoint
class CurrencyPairDetailActivity : AppCompatActivity() {
    lateinit var currencyViewModel: CurrencyPairViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_pair_detail)
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyPairViewModel::class.java)

        val price: TextView = findViewById(R.id.tvPriceDetail)
        val marketCap: TextView = findViewById(R.id.tvMarketCapDetail)
        val volume: TextView = findViewById(R.id.tvTradingVolume)
        val name: TextView = findViewById(R.id.tvDetailName)

        val delete: Button = findViewById(R.id.deleteButton)

        delete.setOnClickListener {
            currencyViewModel.deleteCurrency(ctx = this)
        }

        val chart: LineChart = findViewById(R.id.chart1);


        val id = intent.getStringExtra(COIN_ID)
        val vsCurr = intent.getStringExtra(VS_CURR)

        currencyViewModel.chartData.observe(this, {
            if (it != null) {
                setChartData(chart, it)
            }
        })


        currencyViewModel.currencyPair.observe(this, {
            if (it != null) {
                price.text = "${it.price.format(2)} ${it.vsCurrency}"
                marketCap.text = "${(it.marketCap / 1_000_000).format(2)} M"
                volume.text = "${(it.totalVolume / 1_000_000).format(2)} M"
                name.text = "${it.ticker.toUpperCase()}/${it.vsCurrency.toUpperCase()}"
            }
        })

        currencyViewModel.getCurrency(ctx = this, id = id!!, vsCurr = vsCurr!!)
        currencyViewModel.getCurrencyChartData(id = id, vsCurr = vsCurr)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when( item.itemId){
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    fun setChartData(chart: LineChart, values: List<Pair<Double, Double>>){
        val xAxis = chart.xAxis
        xAxis.isEnabled = false

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false



        val vals = values.map {
            Entry(it.first.toFloat(), it.second.toFloat())
        }

        val set1 = LineDataSet(vals, "Price")

        set1.axisDependency = AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)

        // create a data object with the data sets

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

         // set data
        chart.data = data
        chart.invalidate()
    }
}