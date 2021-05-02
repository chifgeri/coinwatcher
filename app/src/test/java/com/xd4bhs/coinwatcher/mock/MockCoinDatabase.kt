package com.xd4bhs.coinwatcher.mock

import com.xd4bhs.coinwatcher.data.database.AppDatabase
import com.xd4bhs.coinwatcher.data.database.dao.CurrencyDAO
import com.xd4bhs.coinwatcher.data.database.entities.CurrencyPair
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub



fun createMockDatabase(): AppDatabase{

    val mockCurrencyList = ArrayList<CurrencyPair>()

    mockCurrencyList.add(CurrencyPair(id= "myCurrencyPair", vsCurrency = "usd", price = 1000.0, marketCap = 1_000_000.0, totalVolume = 1_000_000.0, ticker = "MCP"))
    mockCurrencyList.add(CurrencyPair(id= "myCurrencyPair2", vsCurrency = "usd", price = 1000.0, marketCap = 1_000_000.0, totalVolume = 1_000_000.0, ticker = "MCP2"))


    val mockDb = mock(AppDatabase::class.java)
    val mockDAO = mock(CurrencyDAO::class.java)
    mockDAO.stub {
        onBlocking { getAllCurrencies() }.doReturn(
            mockCurrencyList
        )
        onBlocking { getSpecificCurrency("myCurrencyPair") }.doReturn(
            mockCurrencyList[0]
        )
        onBlocking { getSpecificCurrencyByVsCurrency("usd")}.doReturn(
            mockCurrencyList
        )

        onBlocking {
            deleteCurrency(mockCurrencyList[0])
        }.thenReturn(Unit)
    }

    `when`(mockDb.currencyDao()).thenReturn(mockDAO)

    return mockDb

}
