package com.non.mornhouse.network

import com.non.mornhouse.network.response.NumberResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{number}?json")
    fun getNumberFact(@Path("number") number: Int): Call<NumberResponse>

    @GET("random/trivia?json")
    fun getRandomNumberFact(): Call<NumberResponse>
}


