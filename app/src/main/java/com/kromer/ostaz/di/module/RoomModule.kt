package com.kromer.ostaz.di.module

import android.content.Context
import androidx.room.Room
import com.kromer.ostaz.data.local.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MyDatabase::class.java, "OstazDatabase")

    @Provides
    @Singleton
    fun providePostsDao(myDatabase: MyDatabase) = myDatabase.getPostsDao()
}