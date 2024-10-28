package com.non.mornhouse.repository

import androidx.lifecycle.LiveData
import com.non.mornhouse.dao.NumberDao
import com.non.mornhouse.data.entities.Number
import com.non.mornhouse.network.ApiService
import com.non.mornhouse.network.response.NumberResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NumberRepository @Inject constructor(
    private val apiService: ApiService,
    private val numberDao: NumberDao
) {
    suspend fun fetchNumberFact(
        number: Int? = null,
        isRandom: Boolean = number == null
    ): NumberResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<NumberResponse> = if (isRandom) {
                apiService.getRandomNumberFact().execute()
            } else {
                number?.let { apiService.getNumberFact(it).execute() }
                    ?: apiService.getRandomNumberFact().execute()
            }

            if (response.isSuccessful) {
                response.body() ?: NumberResponse(
                    text = "",
                    number = number,
                    found = true,
                    type = ""
                )
            } else {
                println("Failed to retrieve fact. Error code: ${response.code()}")
                NumberResponse(
                    text = "Error: Could not retrieve fact",
                    number = number,
                    found = false,
                    type = ""
                )
            }
        }
    }

    suspend fun insertNumbers(numbers: List<Number>) = withContext(Dispatchers.IO) {
        numberDao.insertNumbers(numbers)
    }

    fun getNumbers(): LiveData<List<Number>> = numberDao.getAllNumbers()
}

