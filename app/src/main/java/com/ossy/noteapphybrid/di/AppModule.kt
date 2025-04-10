package com.ossy.noteapphybrid.di



import com.ossy.noteapphybrid.data.api.AuthService
import com.ossy.noteapphybrid.data.api.RetrofitClient
import com.ossy.noteapphybrid.data.datastore.PreferencesManager
import com.ossy.noteapphybrid.data.datastore.UserPreferencesManager
import com.ossy.noteapphybrid.data.local.AppDatabase
import com.ossy.noteapphybrid.repository.AuthRepository
import com.ossy.noteapphybrid.repository.NoteRepository
import com.ossy.noteapphybrid.viewmodel.AuthViewModel
import com.ossy.noteapphybrid.viewmodel.MainViewModel
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
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

