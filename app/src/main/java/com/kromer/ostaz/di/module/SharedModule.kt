package com.kromer.ostaz.di.module

import android.content.Context
import com.kromer.ostaz.data.local.prefs.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) = Preferences(context)

}