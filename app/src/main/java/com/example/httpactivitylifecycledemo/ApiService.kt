package com.example.httpactivitylifecycledemo

import retrofit2.http.GET

interface ApiService {
    @GET("contacts")
    suspend fun getUser():List<UserItem>
}
