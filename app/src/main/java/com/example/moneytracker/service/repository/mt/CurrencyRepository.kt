package com.example.moneytracker.service.repository.mt

import com.example.moneytracker.service.model.mt.Currency
import com.example.moneytracker.service.model.mt.inputs.CurrencyCreateInput
import com.example.moneytracker.service.repository.exchangerate.ExchangerateApi
import com.example.moneytracker.utils.formatToIsoDateWithDashes
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val moneyTrackerApi: MoneyTrackerApi,
    private val exchangerateApi: ExchangerateApi
) {
    suspend fun getUsersCurrencies(): Response<List<Currency>> =
        moneyTrackerApi.api.getUserCurrencies()

    suspend fun getSupportedCurrencies(): Response<List<Currency>> =
        moneyTrackerApi.api.getAllCurrencies()

    suspend fun getUserDefaultCurrency(): Response<Currency> =
        moneyTrackerApi.api.getUserDefaultCurrency()

    suspend fun deleteUserCurrency(userCurrencyId: Int): Response<Currency> =
        moneyTrackerApi.api.deleteUserCurrency(userCurrencyId)

    suspend fun addNewCurrency(currency: CurrencyCreateInput): Response<Currency> =
        moneyTrackerApi.api.saveUserCurrency(currency)

    suspend fun convertCurrency(from: String, to: String, moneyAmount: Double, date: LocalDate): Double {
        val convertedValue =
            exchangerateApi.api.convertCurrency(from, to, date.formatToIsoDateWithDashes(), moneyAmount.toString())

        return convertedValue.result
    }
}