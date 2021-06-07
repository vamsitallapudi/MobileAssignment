package com.backbase.mobileAssignment.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class NetworkModule {

    @Provides
    fun provideCacheFile(context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()
        return cacheFile
    }
}