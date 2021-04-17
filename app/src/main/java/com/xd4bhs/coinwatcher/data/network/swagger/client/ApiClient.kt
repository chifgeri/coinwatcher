package com.xd4bhs.coinwatcher.data.network.swagger.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type

class ApiClient() {
    private var okClient: OkHttpClient? = null
    private var adapterBuilder: Retrofit.Builder? = null


    fun createDefaultAdapter() {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create()
        okClient = OkHttpClient()
        var baseUrl = "https://localhost/api/"
        if (!baseUrl.endsWith("/")) baseUrl = "$baseUrl/"
        adapterBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(GsonCustomConverterFactory.create(gson))
    }

    fun <S> createService(serviceClass: Class<S>?): S? {
        return adapterBuilder?.build()?.create(serviceClass)
    }


    fun getAdapterBuilder(): Retrofit.Builder? {
        return adapterBuilder
    }

    fun setAdapterBuilder(adapterBuilder: Retrofit.Builder?) {
        this.adapterBuilder = adapterBuilder
    }

    fun getOkClient(): OkHttpClient? {
        return okClient
    }

    /**
     * Clones the okClient given in parameter, adds the auth interceptors and uses it to configure the Retrofit
     * @param okClient
     */
    fun configureFromOkclient(okClient: OkHttpClient) {
        val clone: OkHttpClient = okClient.newBuilder().build()
        adapterBuilder?.client(clone)
    }

    init {
        createDefaultAdapter()
    }
}

/**
 * This wrapper is to take care of this case:
 * when the deserialization fails due to JsonParseException and the
 * expected type is String, then just return the body string.
 */
internal class GsonResponseBodyConverterToString<T>(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val returned: String = value.string()
        return try {
            gson.fromJson(returned, type)
        } catch (e: JsonParseException) {
            returned as T
        }
    }
}

internal class GsonCustomConverterFactory private constructor(gson: Gson?) : Converter.Factory() {
    private val gson: Gson
    private val gsonConverterFactory: GsonConverterFactory
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        return if (type == String::class.java) GsonResponseBodyConverterToString<Any>(gson, type) else gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
    }

    fun requestBodyConverter(type: Type?, annotations: Array<Annotation?>?, retrofit: Retrofit?): Converter<*, RequestBody> {
        return gsonConverterFactory.requestBodyConverter(type, annotations, annotations,  retrofit)
    }

    companion object {
        fun create(gson: Gson?): GsonCustomConverterFactory {
            return GsonCustomConverterFactory(gson)
        }
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
        this.gson = gson
        gsonConverterFactory = GsonConverterFactory.create(gson)
    }
}