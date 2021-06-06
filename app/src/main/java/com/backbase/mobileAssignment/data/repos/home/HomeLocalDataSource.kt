package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.data.database.BaseAndroidSkeletonDatabase
import com.backbase.mobileAssignment.data.database.entity.City
import com.backbase.mobileAssignment.utils.NETWORK_ERROR_MSG
import com.backbase.mobileAssignment.utils.Result
import com.backbase.mobileAssignment.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(database: BaseAndroidSkeletonDatabase) {
    suspend fun fetchCities(): Flow<Result<List<City>>> {
        return safeApiCall(call = { fetchCities }, NETWORK_ERROR_MSG)
    }

    private val fetchCities: Flow<Result<List<City>>> = flow {
        val cities = listOf(
            City("Hyderabad"),
            City("Delhi"),
            City("Pune"),
            City("Chennai"),
            City("Kolkata"),
            City("Bangalore"),
            City("Ahmedabad"),
            City("China"),
            City("Bangkok"),
            City("Jaisalmer"),
            City("Udupi"),
            City("Tirupathi"),
            City("Shirdi"),
        )
        emit(Result.Success(cities))
    }
}