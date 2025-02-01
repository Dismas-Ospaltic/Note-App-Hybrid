package com.example.noteapphybrid

import android.app.Application
import com.example.noteapphybrid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin Dependency Injection
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
