package com.backbase.mobileAssignment.di

import android.content.Context
import com.backbase.mobileAssignment.di.modules.StorageModule
import com.backbase.mobileAssignment.di.modules.home.HomeModule
import com.backbase.mobileAssignment.ui.home.HomeActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [HomeModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, @BindsInstance activity: HomeActivity) : AppComponent
    }
    fun inject(activity: HomeActivity)
}