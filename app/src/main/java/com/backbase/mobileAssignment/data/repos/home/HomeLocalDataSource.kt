package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.data.database.entity.City
import com.backbase.mobileAssignment.utils.NETWORK_ERROR_MSG
import com.backbase.mobileAssignment.utils.Result
import com.backbase.mobileAssignment.utils.safeApiCall
import com.backbase.mobileAssignment.utils.search.IDataStructure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(iDataStructure: IDataStructure) {
    suspend fun fetchCities(): Flow<Result<List<City>>> {
        return safeApiCall(call = { fetchCities }, NETWORK_ERROR_MSG)
    }

    private val fetchCities: Flow<Result<List<City>>> = flow {


        iDataStructure.insert("Hyderabad")
        iDataStructure.insert("Delhi")
        iDataStructure.insert("Pune")
        iDataStructure.insert("Chennai")
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