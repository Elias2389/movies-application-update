package com.ae.moviesapplicationupdate.base

import android.app.Application
import com.ae.moviesapplicationupdate.di.appModule
import com.ae.moviesapplicationupdate.di.fragmentModule
import com.ae.moviesapplicationupdate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            //fragmentFactory()
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                listOf(appModule,
                viewModelModule)
            )
        }
    }
}