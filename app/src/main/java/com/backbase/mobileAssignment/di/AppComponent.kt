package com.backbase.mobileAssignment.di

import android.content.Context
import com.backbase.mobileAssignment.di.modules.StorageModule
import com.backbase.mobileAssignment.ui.home.HomeActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [StorageModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
    fun injectActivity(activity: HomeActivity)
}