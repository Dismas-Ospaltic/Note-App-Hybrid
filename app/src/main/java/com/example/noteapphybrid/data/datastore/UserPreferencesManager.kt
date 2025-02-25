
package com.example.noteapphybrid.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ Rename the DataStore extension property
private val Context.userDataStore: DataStore<androidx.datastore.preferences.core.Preferences>
        by preferencesDataStore(name = "user_prefs")

class UserPreferencesManager(private val context: Context) {

    companion object {
        val USER_EMAIL = stringPreferencesKey("user_email")
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val REF_TOKEN = stringPreferencesKey("ref_token")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // Save user data
    suspend fun saveUserData(userEmail: String, authToken: String , refToken: String) {
        context.userDataStore.edit { preferences ->
            preferences[USER_EMAIL] = userEmail
            preferences[AUTH_TOKEN] = authToken
            preferences[REF_TOKEN] = refToken
            preferences[IS_LOGGED_IN] = true
        }
    }

    // Retrieve user data as Flow (to observe changes)
    val userData: Flow<UserData> = context.userDataStore.data.map { preferences ->
        UserData(
            userEmail = preferences[USER_EMAIL] ?: "",
            authToken = preferences[AUTH_TOKEN] ?: "",
            refToken = preferences[REF_TOKEN] ?: "",
            isLoggedIn = preferences[IS_LOGGED_IN] ?: false
        )
    }

    // Clear user data (logout)
    suspend fun clearUserData() {
        context.userDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

// Data class for user data
data class UserData(
    val userEmail: String,
    val authToken: String,
    val refToken: String,
    val isLoggedIn: Boolean
)

