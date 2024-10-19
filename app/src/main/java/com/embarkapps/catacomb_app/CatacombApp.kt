package com.embarkapps.catacomb_app

import android.app.Application
import com.embarkapps.catacomb_app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CatacombApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CatacombApp)
            androidLogger()

            modules(appModule)
        }
    }
}