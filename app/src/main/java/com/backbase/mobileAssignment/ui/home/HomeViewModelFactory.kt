package com.backbase.mobileAssignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val dispatchProvider: CoroutineDispatchProvider,
    private val stockRepo: HomeRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dispatchProvider, stockRepo) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}