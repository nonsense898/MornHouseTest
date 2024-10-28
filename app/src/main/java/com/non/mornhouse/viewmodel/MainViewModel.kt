package com.non.mornhouse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.non.mornhouse.data.entities.Number
import com.non.mornhouse.network.response.NumberResponse
import com.non.mornhouse.repository.NumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository
) : ViewModel() {
    private val _fetchedFact = MutableLiveData<NumberResponse>()
    val storedFacts: LiveData<List<Number>> = repository.getNumbers()

    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex

    fun setCurrentIndex(index: Int) {
        _currentIndex.value = index
    }

    fun fetchNumberFact(number: Int? = null, isRandom: Boolean = number == null) {
        viewModelScope.launch {
            try {
                val factFromApi = repository.fetchNumberFact(number = number, isRandom = isRandom)
                _fetchedFact.value = factFromApi

                factFromApi.number?.let { nonNullNumber ->
                    insertFact(
                        Number(
                            number = nonNullNumber,
                            text = factFromApi.text
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun insertFact(fact: Number) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertNumbers(listOf(fact))
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.printStackTrace()
                }
            }
        }
    }
}


