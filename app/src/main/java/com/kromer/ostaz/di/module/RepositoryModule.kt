package com.kromer.ostaz.di.module

import com.kromer.ostaz.data.repositoryimpl.NotificationsRepoImpl
import com.kromer.ostaz.data.repositoryimpl.PostsRepoImpl
import com.kromer.ostaz.data.source.local.PostsLocalDataSource
import com.kromer.ostaz.data.source.remote.NotificationsRemoteDataSource
import com.kromer.ostaz.data.source.remote.PostsRemoteDataSource
import com.kromer.ostaz.domain.repository.NotificationsRepo
import com.kromer.ostaz.domain.repository.PostsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostsRepo(
        postsRemoteDataSource: PostsRemoteDataSource,
        postsLocalDataSource: PostsLocalDataSource
    ): PostsRepo {
        return PostsRepoImpl(postsRemoteDataSource, postsLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideNotificationsRepo(
        notificationsRemoteDataSource: NotificationsRemoteDataSource
    ): NotificationsRepo {
        return NotificationsRepoImpl(notificationsRemoteDataSource)
    }
}
