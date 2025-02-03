package com.example.noteapphybrid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapphybrid.data.datastore.UserPreferencesManager
import kotlinx.coroutines.launch

class AuthViewModel(private val userPreferences: UserPreferencesManager) : ViewModel() {

    fun loginUser(userId: String, userEmail: String, authToken: String , refToken: String) {
        viewModelScope.launch {
            userPreferences.saveUserData(userId, userEmail, authToken , refToken)
        }
    }
}
