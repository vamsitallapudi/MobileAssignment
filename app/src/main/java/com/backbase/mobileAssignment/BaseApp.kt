package com.backbase.mobileAssignment

import android.app.Application
import com.backbase.mobileAssignment.di.AppComponent
import com.backbase.mobileAssignment.di.DaggerAppComponent

class BaseApp : Application() {
    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
    override fun onCreate() {
        super.onCreate()
    }
}