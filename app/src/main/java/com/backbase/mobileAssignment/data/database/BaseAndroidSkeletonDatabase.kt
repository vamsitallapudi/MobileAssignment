package com.backbase.mobileAssignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.backbase.mobileAssignment.data.database.dao.HomeDao
import com.backbase.mobileAssignment.data.database.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class BaseAndroidSkeletonDatabase: RoomDatabase() {
    abstract fun homeDao() : HomeDao

    companion object {
        private const val DATABASE_NAME = "base_android_skeleton"

        @Volatile
        private var instance: BaseAndroidSkeletonDatabase? = null

        fun getDatabase(context: Context) : BaseAndroidSkeletonDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context) : BaseAndroidSkeletonDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                BaseAndroidSkeletonDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}