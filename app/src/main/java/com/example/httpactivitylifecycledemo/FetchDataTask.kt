package com.example.httpactivitylifecycledemo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FetchDataTask {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://playground.mockoon.com/") // Base URL of your API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun fetchUsers(): List<UserItem> {
        return apiService.getUser()
    }
}


