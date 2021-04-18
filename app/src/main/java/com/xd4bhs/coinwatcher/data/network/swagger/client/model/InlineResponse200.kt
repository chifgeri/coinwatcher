package com.xd4bhs.coinwatcher.data.network.swagger.client.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class InlineResponse200 {
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val inlineResponse200 = o as InlineResponse200
        return true
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class InlineResponse200 {\n")
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