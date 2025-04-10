package com.ossy.noteapphybrid.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://6aa0-154-159-237-95.ngrok-free.app/HybridNoteAppBackend/"

    // Creates and returns the Retrofit instance
    fun createAuthService(): AuthService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }
}
