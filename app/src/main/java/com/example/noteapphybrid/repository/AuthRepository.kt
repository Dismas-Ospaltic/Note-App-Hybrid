package com.example.noteapphybrid.repository



import com.example.noteapphybrid.data.api.AuthService
import com.example.noteapphybrid.model.LoginRequest
import com.example.noteapphybrid.model.LoginResponse
import com.example.noteapphybrid.model.RegisterRequest
import com.example.noteapphybrid.model.RegisterResponse
import retrofit2.Response

// Repository handles API calls and business logic
class AuthRepository(private val authService: AuthService) {

    // Login function
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return authService.login(LoginRequest(email, password))
    }

    // Register function
    suspend fun register(email: String, password: String,): Response<RegisterResponse> {
        return authService.register(RegisterRequest(email, password))
    }
}
