package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.util.*

class CurrencyPairInfo {
    /**
     */
    @SerializedName("id")
    var id: String? = null


    @SerializedName("symbol")
    var symbol: String? = null

    /**
     */
    @SerializedName("name")
    var name: String? = null

    /**
     */

    @SerializedName("current_price")
    var currentPrice: Double? = null

    /**
     */
    @SerializedName("market_cap")
    var marketCap: Double? = null

    /**
     */
    @SerializedName("total_volume")
    var totalVolume: Double? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val currencyPairInfo = o as CurrencyPairInfo
        return id == currencyPairInfo.id &&
                symbol == currencyPairInfo.symbol &&
                name == currencyPairInfo.name &&
                currentPrice == currencyPairInfo.currentPrice &&
                marketCap == currencyPairInfo.marketCap &&
                totalVolume == currencyPairInfo.totalVolume
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(id, symbol, name, currentPrice, marketCap, totalVolume)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CurrencyPairInfo {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    currentPrice: ").append(toIndentedString(currentPrice)).append("\n")
        sb.append("    marketCap: ").append(toIndentedString(marketCap)).append("\n")
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