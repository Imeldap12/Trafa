package com.example.trafa.Model

import retrofit2.http.GET

interface ApiService {
    @GET("desc/nama")
    suspend fun getUlosData(): UlosResponse
}