package com.backbase.mobileAssignment.di.modules.home

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.backbase.mobileAssignment.di.modules.StorageModule
import com.backbase.mobileAssignment.ui.home.HomeActivity
import com.backbase.mobileAssignment.ui.home.HomeViewModel
import com.backbase.mobileAssignment.ui.home.HomeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [StorageModule::class])
abstract class HomeModule {

    @Binds
    abstract fun homeActivityAsFragmentActivity(activity: HomeActivity): FragmentActivity

    @Binds
    abstract fun homeActivityAsActivity(activity: HomeActivity): Activity

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideHomeViewModel(
            factory: HomeViewModelFactory,
            fragmentActivity: FragmentActivity
        ): HomeViewModel {
            return ViewModelProvider(fragmentActivity, factory)
                .get(HomeViewModel::class.java)
        }
    }
}