package com.backbase.mobileAssignment.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

suspend fun <T:Any> safeApiCall(call : suspend () -> Flow<Result<T>>, errorMessage: String) : Flow<Result<T>> {
    return try {
        call()
    } catch (e : Exception) {
        return flow { emit(Result.Error(IOException(errorMessage, e))) }
    }
}