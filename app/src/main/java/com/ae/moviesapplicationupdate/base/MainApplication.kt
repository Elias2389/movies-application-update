package com.ae.moviesapplicationupdate.base

import android.app.Application
import com.ae.moviesapplicationupdate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            fragmentFactory()
            androidLogger()
            androidContext(this@MainApplication)

            modules(appModule)
        }
    }
}