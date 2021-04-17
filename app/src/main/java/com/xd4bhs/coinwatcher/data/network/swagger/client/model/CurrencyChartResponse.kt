package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.util.*

class CurrencyChartResponse {
    /**
     */
    @SerializedName("prices")
    var prices: List<List<Double>> = ArrayList()
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val currencyChartResponse = o as CurrencyChartResponse
        return prices == currencyChartResponse.prices
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(prices)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CurrencyChartResponse {\n")
        sb.append("    prices: ").append(toIndentedString(prices)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}