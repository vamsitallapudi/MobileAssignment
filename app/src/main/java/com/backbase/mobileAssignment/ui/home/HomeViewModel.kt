package com.backbase.mobileAssignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.mobileAssignment.data.database.entity.Movie
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.ui.base.BaseViewModel
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import com.backbase.mobileAssignment.utils.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel (val dispatchProvider: CoroutineDispatchProvider, val repo:HomeRepo) : BaseViewModel() {

    private val moviesMutableLiveData = MutableLiveData<List<Movie>>()
    val stockLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData


    fun fetchMovies() : Job {
        return viewModelScope.launch(dispatchProvider.io) {
            withContext(dispatchProvider.main) {
                showLoading()
            }
            val result = repo.fetchMovies()
            result.collect {
                when (it) {
                    is Result.Success<*> -> {
                        hideLoading()
                        val movieList = it.data
                        moviesMutableLiveData.postValue(movieList as List<Movie>?)
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