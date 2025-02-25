package com.example.noteapphybrid.di



import com.example.noteapphybrid.data.api.AuthService
import com.example.noteapphybrid.data.api.RetrofitClient
import com.example.noteapphybrid.data.datastore.PreferencesManager
import com.example.noteapphybrid.data.datastore.UserPreferencesManager
import com.example.noteapphybrid.data.local.AppDatabase
import com.example.noteapphybrid.repository.AuthRepository
import com.example.noteapphybrid.repository.NoteRepository
import com.example.noteapphybrid.viewmodel.AuthViewModel
import com.example.noteapphybrid.viewmodel.MainViewModel
import com.example.noteapphybrid.viewmodel.NoteViewModel
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
//    single { NotesRepository() }  // Provide NotesRepository as a singleton
//    viewModel { NotesViewModel(get()) }  // Inject NotesViewModel with Koin
//    single { AppDatabase.getDatabase(context = get()).noteDao() }
//    single { NoteRepository(noteDao = get()) }
//    viewModel { NoteViewModel(repository = get()) }

    single { AppDatabase.getDatabase(get()).noteDao() }
    single { NoteRepository(get()) }
    viewModel { NoteViewModel(get()) }



    single<AuthService> { RetrofitClient.createAuthService() }
    single { AuthRepository(get()) }
//    viewModel { AuthViewModel(get()) }
    viewModel { AuthViewModel(get(), get()) }
}

