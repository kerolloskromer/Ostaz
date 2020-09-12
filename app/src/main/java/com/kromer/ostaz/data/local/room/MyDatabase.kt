package com.kromer.ostaz.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kromer.ostaz.data.source.local.PostsDao
import com.kromer.ostaz.domain.entity.Post

@Database(entities = [Post::class], version = MyDatabase.DATABASE_VERSION)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "OstazDatabase"
    }

    abstract fun getPostsDao(): PostsDao
}