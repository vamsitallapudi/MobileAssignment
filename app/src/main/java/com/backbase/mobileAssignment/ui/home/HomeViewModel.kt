package com.backbase.mobileAssignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.mobileAssignment.data.database.entity.City
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.ui.base.BaseViewModel
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import com.backbase.mobileAssignment.utils.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val dispatchProvider: CoroutineDispatchProvider, private val repo:HomeRepo) : BaseViewModel() {

    private val citiesMutableLiveData = MutableLiveData<List<City>>()
    val citiesLiveData: LiveData<List<City>>
        get() = citiesMutableLiveData


    fun fetchCities() : Job {
        return viewModelScope.launch(dispatchProvider.io) {
            withContext(dispatchProvider.main) {
                showLoading()
            }
            val result = repo.fetchCities()
            result.collect {
                when (it) {
                    is Result.Success<*> -> {
                        hideLoading()
                        citiesMutableLiveData.postValue(it.data as List<City>?)
                    }
                    is Result.Error -> {

                    }
                    is Result.Loading -> {

                    }
                }
            }
        }
    }
}