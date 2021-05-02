package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

class CurrencyAddRequest {
    /**
     */
    @SerializedName("name")
    var name: String? = null

    /**
     */
    @SerializedName("ticker")
    var ticker: String? = null

    /**
     */
    @SerializedName("currenctPrice")
    var currenctPrice: Double? = null

    /**
     */
    @SerializedName("vsCurrencyId")
    var vsCurrencyId: BigDecimal? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val currencyAddRequest = o as CurrencyAddRequest
        return name == currencyAddRequest.name &&
                ticker == currencyAddRequest.ticker &&
                currenctPrice == currencyAddRequest.currenctPrice &&
                vsCurrencyId == currencyAddRequest.vsCurrencyId
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(name, ticker, currenctPrice, vsCurrencyId)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class CurrencyAddRequest {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    ticker: ").append(toIndentedString(ticker)).append("\n")
        sb.append("    currenctPrice: ").append(toIndentedString(currenctPrice)).append("\n")
        sb.append("    vsCurrencyId: ").append(toIndentedString(vsCurrencyId)).append("\n")
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