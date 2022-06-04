package com.example.moneytracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneytracker.service.model.Category
import com.example.moneytracker.service.model.Currency
import com.example.moneytracker.service.model.Operation
import com.example.moneytracker.service.model.createinputs.OperationCreateInput
import com.example.moneytracker.service.repository.internal.CategoryRepository
import com.example.moneytracker.service.repository.internal.CurrencyRepository
import com.example.moneytracker.service.repository.internal.OperationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddOperationViewModel @Inject constructor(
    private val operationRepository: OperationRepository,
    private val categoryRepository: CategoryRepository,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val currenciesResponse: MutableLiveData<List<Currency>> = MutableLiveData()
    private val categoriesResponse: MutableLiveData<List<Category>> = MutableLiveData()
    private val operationSaveResponse: MutableLiveData<Operation> = MutableLiveData()

    suspend fun addNewOperation(
        name: String,
        moneyAmount: Double,
        date: LocalDate,
        categoryName: String,
        currencyName: String
    ): LiveData<Operation> {
        val operationCreateInput =
            OperationCreateInput(name, moneyAmount, date.toString(), categoryName, currencyName)

        operationSaveResponse.value = operationRepository.addNewOperation(operationCreateInput)

        return operationSaveResponse
    }

    suspend fun getAllCurrencies(): MutableLiveData<List<Currency>> {
        currenciesResponse.value = currencyRepository.getAllCurrencies()

        return currenciesResponse
    }

    suspend fun getAllCategories(): MutableLiveData<List<Category>> {
        categoriesResponse.value = categoryRepository.getAllCategories()

        return categoriesResponse
    }
}