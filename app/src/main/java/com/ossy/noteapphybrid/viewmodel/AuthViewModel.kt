package com.ossy.noteapphybrid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ossy.noteapphybrid.data.datastore.UserPreferencesManager
import com.ossy.noteapphybrid.model.LoginResponse
import com.ossy.noteapphybrid.repository.AuthRepository
import com.google.gson.Gson
import com.ossy.noteapphybrid.model.RegisterResponse
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class AuthViewModel(private val userPreferences: UserPreferencesManager, private val repository: AuthRepository) : ViewModel() {

    var loginResponse: LoginResponse? = null
        private set

    fun login(email: String, password: String, onResult: (Boolean, String, String?, String?, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> = repository.login(email, password)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        if (body.status == "success") {
                            val token = body.data["access_token"] as? String
                            val refreshToken = body.data["refresh_token"] as? String
//                            val userEmail = body.data["email"] as? String
                            val userEmail = email

                            // Save user data if token is available
                            if (!token.isNullOrEmpty()) {
                                userPreferences.saveUserData(userEmail ?: "", token, refreshToken ?:"")
                            }

                            onResult(true, body.message, token, refreshToken, userEmail)
                        } else {
                            onResult(false, body.message, null, null, null) // If status == "error"
                        }
                    } ?: onResult(false, "Unexpected response", null, null, null)
                } else {
                    // Extract server error message
                    val errorMessage = extractErrorMessage(response)
                    onResult(false, errorMessage, null, null, null)
                }
            } catch (e: IOException) {
                onResult(false, "Network error. Please check your connection.", null, null, null)
            } catch (e: Exception) {
                onResult(false, "An unexpected error occurred.", null, null, null)
            }
        }
    }


    fun register(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> = repository.register(email, password)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        if (body.status == "success") {
//                            val token = body.data["access_token"] as? String
//                            val refreshToken = body.data["refresh_token"] as? String
//                            val userEmail = email

                            onResult(true, body.message)
                        } else {
                            onResult(false, body.message)
                        }
                    } ?: onResult(false, "Unexpected response")
                } else {
                    val errorMessage = extractErrorMessage(response)
                    onResult(false, errorMessage)
                }
            } catch (e: IOException) {
                onResult(false, "Network error. Please check your connection.")
            } catch (e: Exception) {
                onResult(false, "An unexpected error occurred.")
            }
        }
    }






    // Extracts the server error message from errorBody
    private fun extractErrorMessage(response: Response<*>): String {
        return try {
            val errorBody = response.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            errorResponse?.message ?: "Unknown error"
        } catch (e: Exception) {
            "Failed to parse error response"
        }
    }
}


