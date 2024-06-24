package com.development.lomboknews.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.development.lomboknews.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 4, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NewsDatabase {
            if (INSTANCE == null) {
                synchronized(NewsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "bleep_database")
                        .build()
                }
            }
            return INSTANCE as NewsDatabase
        }
    }
}