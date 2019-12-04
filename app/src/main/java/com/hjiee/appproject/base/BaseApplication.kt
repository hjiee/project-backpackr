package com.hjiee.appproject.base

import android.app.Application
import com.hjiee.appproject.di.appModule
import com.hjiee.appproject.di.networkModule
import com.hjiee.appproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(networkModule, viewModelModule, appModule))
        }
    }
}