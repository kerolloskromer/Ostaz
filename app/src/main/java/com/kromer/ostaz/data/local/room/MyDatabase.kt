package com.kromer.ostaz.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kromer.ostaz.data.model.Post
import com.kromer.ostaz.data.source.local.PostsDao

@Database(entities = [Post::class], version = MyDatabase.CURRENT_DATABASE_VERSION)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        const val CURRENT_DATABASE_VERSION = 1
    }

    abstract fun getPostsDao(): PostsDao
}