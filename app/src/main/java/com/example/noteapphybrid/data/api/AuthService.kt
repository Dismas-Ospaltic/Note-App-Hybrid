package com.example.noteapphybrid.data.api



import com.example.noteapphybrid.model.LoginRequest
import com.example.noteapphybrid.model.LoginResponse
import com.example.noteapphybrid.model.RegisterRequest
import com.example.noteapphybrid.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

// Defines authentication API endpoints
interface AuthService {

    // Login endpoint
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Register (Signup) endpoint
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
