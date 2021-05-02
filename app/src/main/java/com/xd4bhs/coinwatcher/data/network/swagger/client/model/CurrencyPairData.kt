package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.util.*

class CurrencyPairData {
    /**
     */
    @SerializedName("id")
    var id: String? = null

    /**
     */
    @SerializedName("symbol")
    var symbol: String? = null

    /**
     */
    @SerializedName("market_data")
    var marketData: CurrencyPairDataMarketData? = null

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val currencyPairData = o as CurrencyPairData
        return id == currencyPairData.id &&
                symbol == currencyPairData.symbol &&
                marketData == currencyPairData.marketData
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(id, symbol, marketData)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CurrencyPairData {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n")
        sb.append("    marketData: ").append(toIndentedString(marketData)).append("\n")
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