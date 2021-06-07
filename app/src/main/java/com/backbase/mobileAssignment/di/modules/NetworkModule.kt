package com.backbase.mobileAssignment.di.modules

import android.content.Context
import com.backbase.mobileAssignment.data.models.City
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

    @Provides
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideMoshiAdapter(moshi: Moshi) : JsonAdapter<List<City?>?> {
        val cityListType = Types.newParameterizedType(
            MutableList::class.java,
            City::class.java
        )
        return moshi.adapter(cityListType)
    }
}