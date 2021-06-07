package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.utils.NETWORK_ERROR_MSG
import com.backbase.mobileAssignment.utils.Result
import com.backbase.mobileAssignment.utils.safeApiCall
import com.backbase.mobileAssignment.utils.search.SearchUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(searchUtils: SearchUtils) {
    private lateinit var prefix: String
    suspend fun fetchCities(): Flow<Result<List<City?>>> {
        return safeApiCall(call = { fetchCities }, NETWORK_ERROR_MSG)
    }

    suspend fun insertCities(): Flow<Result<Boolean>> {
        return safeApiCall(call = { insertCities }, NETWORK_ERROR_MSG)
    }
    suspend fun getSuggestions(prefix:String): Flow<Result<List<City?>>> {
        this.prefix = prefix
        return safeApiCall(call = { getSuggestions }, NETWORK_ERROR_MSG)
    }

    private val fetchCities: Flow<Result<List<City?>>> = flow {
        try {
            val list = searchUtils.fetchAll()
            emit(Result.Success(list))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
    private val insertCities: Flow<Result<Boolean>> = flow {
        try {
            val cities = listOf(
                City("Hyderabad"),
                City("Delhi"),
                City("Pune"),
                City("Chennai"),
                City("Tirupathi"),
                City("Shirdi"),
                City("Ooty"),
                City("Kodai canal"),
                City("Verrières -l(Kreis 3) \\/ e- Buisson Shāhrūd"),
            )
            for (city in cities)
                searchUtils.insert(city)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
    private val getSuggestions: Flow<Result<List<City?>>> = flow {
        try {
            val list = searchUtils.getSuggestions(prefix)
            emit(Result.Success(list))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}