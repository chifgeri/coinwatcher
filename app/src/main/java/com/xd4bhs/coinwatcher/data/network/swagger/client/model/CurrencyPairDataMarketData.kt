package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.util.*

class CurrencyPairDataMarketData {
    /**
     */
    @SerializedName("market_cap")
    var marketCap: Any? = null

    /**
     */
    @SerializedName("current_price")
    var currentPrice: Any? = null

    @SerializedName("total_volume")
    var totalVolume: Any? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val currencyPairDataMarketData = o as CurrencyPairDataMarketData
        return marketCap == currencyPairDataMarketData.marketCap &&
                currentPrice == currencyPairDataMarketData.currentPrice &&
                totalVolume == currencyPairDataMarketData.totalVolume
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(marketCap, currentPrice, totalVolume)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CurrencyPairDataMarketData {\n")
        sb.append("    marketCap: ").append(toIndentedString(marketCap)).append("\n")
        sb.append("    currentPrice: ").append(toIndentedString(currentPrice)).append("\n")
        sb.append("    totalVolume: ").append(toIndentedString(totalVolume)).append("\n")
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