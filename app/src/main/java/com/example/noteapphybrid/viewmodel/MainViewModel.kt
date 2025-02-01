package com.example.noteapphybrid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapphybrid.data.datastore.PreferencesManager
import kotlinx.coroutines.launch

class MainViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    // Exposed Flow for Onboarding state
    val isOnboardingCompleted = preferencesManager.isOnboardingCompleted

    // Method to complete onboarding
    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesManager.completeOnboarding()
        }
    }
}
