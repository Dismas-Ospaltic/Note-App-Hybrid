package com.ossy.noteapphybrid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ossy.noteapphybrid.data.datastore.PreferencesManager
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
