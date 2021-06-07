package com.backbase.mobileAssignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.ui.base.BaseViewModel
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import com.backbase.mobileAssignment.utils.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val dispatchProvider: CoroutineDispatchProvider,
    private val repo: HomeRepo
) : BaseViewModel() {

    private val fetchCitiesMutableLiveData = MutableLiveData<List<City>>()
    private val insertCitiesMutableLiveData = MutableLiveData<Boolean>()

    val fetchCitiesLiveData: LiveData<List<City>>
        get() = fetchCitiesMutableLiveData
    val insertCitiesLiveData: LiveData<Boolean>
        get() = insertCitiesMutableLiveData


    fun fetchCities(): Job {
        return viewModelScope.launch(dispatchProvider.io) {
            withContext(dispatchProvider.main) {
                showLoading()
            }
            val result = repo.fetchCities()
            result.collect {
                when (it) {
                    is Result.Success<*> -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                        fetchCitiesMutableLiveData.postValue(it.data as List<City>?)
                    }
                    is Result.Error -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                    }
                    else -> {}
                }
            }
        }
    }
    fun getSuggestions(prefix: String): Job {
        return viewModelScope.launch(dispatchProvider.io) {
            withContext(dispatchProvider.main) {
                showLoading()
            }
            val result = repo.getSuggestions(prefix)
            result.collect {
                when (it) {
                    is Result.Success<*> -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                        fetchCitiesMutableLiveData.postValue(it.data as List<City>?)
                    }
                    is Result.Error -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun insertCities(): Job {
        return viewModelScope.launch(dispatchProvider.io) {
            withContext(dispatchProvider.main) {
                showLoading()
            }
            val result = repo.insertCities()
            result.collect {
                when (it) {
                    is Result.Success<*> -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                        insertCitiesMutableLiveData.postValue(it.data as Boolean)
                    }
                    is Result.Error -> {
                        withContext(dispatchProvider.main) {
                            hideLoading()
                        }
                    }
                    is Result.Loading -> {

                    }
                }
            }
        }
    }
}