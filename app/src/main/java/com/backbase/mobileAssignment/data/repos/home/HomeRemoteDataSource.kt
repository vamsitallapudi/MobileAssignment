package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.data.api.BaseService
import com.backbase.mobileAssignment.data.database.entity.Movie
import com.backbase.mobileAssignment.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(val service: BaseService) : HomeDataSource {


    suspend fun fetchMovies(): Flow<Result<List<Movie>>> {
        return safeApiCall(call = {fetchMovies}, NETWORK_ERROR_MSG)
    }

    private val fetchMovies : Flow<Result<List<Movie>>> = flow {
        val response = service.getPopularMoviesAsync("popularity.desc", API_KEY).await()
        if(response.isSuccessful) {
            val movies = response.body()?.result
            movies?.let {
                emit(Result.Success(it))
            }
        } else {
            emit(Result.Error(IOException(NETWORK_ERROR_MSG)))
        }
    }
}