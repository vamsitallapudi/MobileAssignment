package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepo @Inject
constructor(private val homeLocalDataSource: HomeLocalDataSource) {
    suspend fun fetchAllCities(): Flow<Result<*>> {
        return homeLocalDataSource.fetchAllCities()
    }
    suspend fun insertCities(): Flow<Result<*>> {
        return homeLocalDataSource.insertCities()
    }

    suspend fun getSuggestions(prefix: String): Flow<Result<*>> {
        return homeLocalDataSource.getSuggestions(prefix)
    }

}