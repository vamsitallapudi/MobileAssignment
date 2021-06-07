package com.backbase.mobileAssignment.di.modules

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.backbase.mobileAssignment.ui.home.HomeActivity
import com.backbase.mobileAssignment.ui.home.HomeViewModel
import com.backbase.mobileAssignment.ui.home.HomeViewModelFactory
import com.backbase.mobileAssignment.utils.search.IDataStructure
import com.backbase.mobileAssignment.utils.search.TrieDS
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [StorageModule::class])
abstract class HomeModule {

    @Binds
    abstract fun homeActivityAsFragmentActivity(activity: HomeActivity): FragmentActivity

    @Binds
    abstract fun homeActivityAsActivity(activity: HomeActivity): Activity
    @Binds
    abstract fun provideTrieAsIDataStructure(trieDS: TrieDS): IDataStructure

    companion object {
        @Provides
        fun provideHomeViewModel(
            factory: HomeViewModelFactory,
            fragmentActivity: FragmentActivity
        ): HomeViewModel {
            return ViewModelProvider(fragmentActivity, factory)
                .get(HomeViewModel::class.java)
        }

        @Provides
        fun provideTrie(): TrieDS {
            return TrieDS.instance
        }
    }
}