package com.backbase.mobileAssignment.data.repos.home

import android.content.Context
import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.utils.*
import com.backbase.mobileAssignment.utils.search.SearchUtils
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(context: Context, searchUtils: SearchUtils, moshiAdapter: JsonAdapter<List<City?>?>) {
    private lateinit var prefix: String
    suspend fun fetchAllCities(): Flow<Result<List<City?>>> {
        return safeApiCall(call = { fetchAllCities }, NETWORK_ERROR_MSG)
    }

    suspend fun insertCities(): Flow<Result<Boolean>> {
        return safeApiCall(call = { insertCities }, NETWORK_ERROR_MSG)
    }
    suspend fun getSuggestions(prefix:String): Flow<Result<List<City?>>> {
        this.prefix = prefix
        return safeApiCall(call = { getSuggestions }, NETWORK_ERROR_MSG)
    }

    private val fetchAllCities: Flow<Result<List<City?>>> = flow {
        try {
            val list = searchUtils.fetchAll()
            emit(Result.Success(list))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private val insertCities: Flow<Result<Boolean>> = flow {

        val data = FileUtils.readJsonFromAssets(context,"cities.json")
        try {
            data?.let {
                val cities = moshiAdapter.fromJson(data) ?: return@flow
                for (city in cities)
                    searchUtils.insert(city)
                emit(Result.Success(true))
            }
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