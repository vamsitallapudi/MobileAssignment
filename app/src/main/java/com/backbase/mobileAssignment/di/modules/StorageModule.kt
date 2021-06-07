package com.backbase.mobileAssignment.di.modules

import com.backbase.mobileAssignment.data.repos.home.HomeLocalDataSource
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import com.backbase.mobileAssignment.utils.search.IDataStructure
import com.backbase.mobileAssignment.utils.search.SearchUtils
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
    fun provideHomeLocalDataSource(searchUtils: SearchUtils) : HomeLocalDataSource {
        return HomeLocalDataSource(searchUtils)
    }

    @Provides
    fun provideCoroutineDispatchProvider(): CoroutineDispatchProvider {
        return CoroutineDispatchProvider()
    }
}