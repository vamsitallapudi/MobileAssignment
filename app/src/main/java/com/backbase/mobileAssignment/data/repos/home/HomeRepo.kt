package com.backbase.mobileAssignment.data.repos.home

import com.backbase.mobileAssignment.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepo @Inject
constructor(private val homeLocalDataSource: HomeLocalDataSource) {
    suspend fun fetchCities(): Flow<Result<*>> {
        return homeLocalDataSource.fetchCities()
    }

}