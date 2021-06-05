package com.backbase.mobileAssignment.di.modules

import android.content.Context
import com.backbase.mobileAssignment.data.api.BaseService
import com.backbase.mobileAssignment.data.database.BaseAndroidSkeletonDatabase
import com.backbase.mobileAssignment.data.repos.home.HomeLocalDataSource
import com.backbase.mobileAssignment.data.repos.home.HomeRemoteDataSource
import com.backbase.mobileAssignment.data.repos.home.HomeRepo
import com.backbase.mobileAssignment.utils.CoroutineDispatchProvider
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class StorageModule{

    @Provides
    fun provideDatabase(context: Context) : BaseAndroidSkeletonDatabase {
        return BaseAndroidSkeletonDatabase.getDatabase(context)
    }

    @Provides
    fun provideHomeRepo(localDataSource: HomeLocalDataSource, remoteDataSource: HomeRemoteDataSource) : HomeRepo {
        return HomeRepo(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideHomeLocalDataSource(database: BaseAndroidSkeletonDatabase) : HomeLocalDataSource {
        return HomeLocalDataSource(database)
    }
    @Provides
    fun provideHomeRemoteDataSource(service : BaseService) : HomeRemoteDataSource {
        return HomeRemoteDataSource(service)
    }

    @Provides
    fun provideCoroutineDispatchProvider(): CoroutineDispatchProvider {
        return CoroutineDispatchProvider()
    }
}