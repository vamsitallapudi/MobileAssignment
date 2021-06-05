package com.backbase.mobileAssignment.di.modules

import android.content.Context
import androidx.databinding.library.BuildConfig
import com.backbase.mobileAssignment.data.api.BaseService
import com.backbase.mobileAssignment.data.api.BaseService.Companion.BASE_URL
import com.backbase.mobileAssignment.utils.CACHE_SIZE
import com.backbase.mobileAssignment.utils.NETWORK_TIMEOUT
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
//            addInterceptor(networkInterceptor)
            connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            cache(cache)
        }.build()
    }

//    TODO: Use this in case of headers are required
//    private val networkInterceptor = Interceptor { chain ->
//        val builder = chain.request().newBuilder()
//        builder.header(YAHOO_API_ID_KEY, YAHOO_API_ID)
//        builder.header(YAHOO_API_HOST_KEY, YAHOO_API_HOST)
//        builder.header("useQueryString", "true")
//        return@Interceptor chain.proceed(builder.build())
//    }

    @Provides
    fun provideCacheFile(context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()
        return cacheFile
    }

    @Provides
    fun provideCache(file: File): Cache {
        return Cache(file, CACHE_SIZE)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val debugLevel = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor().apply {
            level = debugLevel
        }
    }

    @Provides
    fun provideBaseService(okHttpClient: OkHttpClient): BaseService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))//TODO: create these with dagger too.
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(BaseService::class.java)
    }
}