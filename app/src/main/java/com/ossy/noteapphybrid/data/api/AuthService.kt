package com.ossy.noteapphybrid.data.api



import com.ossy.noteapphybrid.model.LoginRequest
import com.ossy.noteapphybrid.model.LoginResponse
import com.ossy.noteapphybrid.model.RegisterRequest
import com.ossy.noteapphybrid.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.PUT

// Defines authentication API endpoints
interface AuthService {

    // Login endpoint
    @POST("auth/login.php")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Register (Signup) endpoint
    @POST("auth/register.php")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    // refresh (get new access token) endpoint
    @POST("auth/refresh.php")
    suspend fun refreshToken(@Body request: RegisterRequest): Response<RegisterResponse>


    // add the list of notes endpoint
    @POST("noteManagement/addNote.php")
    suspend fun addNote(@Body request: RegisterRequest): Response<RegisterResponse>

    // delete the list of notes endpoint
    @DELETE("noteManagement/deleteNote.php")
    suspend fun deleteNote(@Body request: RegisterRequest): Response<RegisterResponse>

    // delete the list of notes endpoint
    @PUT("noteManagement/updateNote.php")
    suspend fun updateNote(@Body request: RegisterRequest): Response<RegisterResponse>








}
