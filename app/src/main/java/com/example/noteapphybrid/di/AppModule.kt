package com.example.noteapphybrid.di



import com.example.noteapphybrid.data.datastore.PreferencesManager
import com.example.noteapphybrid.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Define ViewModel injection
    viewModel { MainViewModel(get()) }

    // Define PreferencesManager injection
    single { PreferencesManager(get()) }
}
