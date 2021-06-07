package com.backbase.mobileAssignment.di.modules

import android.content.Context
import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.data.repos.home.HomeLocalDataSource
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import com.backbase.mobileAssignment.utils.search.IDataStructure
import com.backbase.mobileAssignment.utils.search.SearchUtils
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class StorageModule{

    @Provides
    fun provideHomeRepo(localDataSource: HomeLocalDataSource) : HomeRepo {
        return HomeRepo(localDataSource)
    }

    @Provides
    fun provideSearchUtils(iDataStructure: IDataStructure) : SearchUtils {
        return SearchUtils.getInstance(iDataStructure)
    }
    @Provides
    fun provideHomeLocalDataSource(context: Context, searchUtils: SearchUtils, moshiAdapter: JsonAdapter<List<City?>?>) : HomeLocalDataSource {
        return HomeLocalDataSource(context, searchUtils, moshiAdapter)
    }

    @Provides
    fun provideCoroutineDispatchProvider(): CoroutineDispatchProvider {
        return CoroutineDispatchProvider()
    }
}