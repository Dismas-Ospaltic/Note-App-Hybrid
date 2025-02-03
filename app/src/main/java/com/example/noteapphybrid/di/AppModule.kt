package com.example.noteapphybrid.di



import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.noteapphybrid.data.datastore.PreferencesManager
import com.example.noteapphybrid.data.datastore.UserPreferencesManager
import com.example.noteapphybrid.repository.NotesRepository
import com.example.noteapphybrid.viewmodel.MainViewModel
import com.example.noteapphybrid.viewmodel.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module




val appModule = module {
    // Define ViewModel injection
    viewModel { MainViewModel(get()) }

    // Define PreferencesManager injection
    single { PreferencesManager(get()) }

    // Provide UserPreferencesManager for User Data
    single { UserPreferencesManager(androidContext()) }

    //notes management
    single { NotesRepository() }  // Provide NotesRepository as a singleton
    viewModel { NotesViewModel(get()) }  // Inject NotesViewModel with Koin
}
