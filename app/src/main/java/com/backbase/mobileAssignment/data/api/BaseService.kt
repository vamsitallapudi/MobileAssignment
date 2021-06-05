package com.backbase.mobileAssignment.data.api

import com.backbase.mobileAssignment.data.models.MoviesResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    @GET("discover/movie")
    fun getPopularMoviesAsync(
        @Query("sort_by") sortBy:String,
        @Query("api_key") apiKey: String,
    ) : Deferred<Response<MoviesResult>>


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}