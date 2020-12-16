package com.example.testfour

import android.app.Application
import com.example.testfour.di.appModule
import com.example.testfour.di.networkingModule
import com.example.testfour.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StoriesApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StoriesApp)
            modules(listOf(appModule(), networkingModule(), presenterModule()))
        }

        if (BuildConfig.DEBUG) System.setProperty("kotlinx.coroutines.debug", "on")
    }
}