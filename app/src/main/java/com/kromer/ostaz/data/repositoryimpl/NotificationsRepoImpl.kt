package com.kromer.ostaz.data.repositoryimpl

import com.kromer.ostaz.data.model.RegisterFCMRequest
import com.kromer.ostaz.data.source.remote.NotificationsRemoteDataSource
import com.kromer.ostaz.domain.repository.NotificationsRepo
import javax.inject.Inject


class NotificationsRepoImpl @Inject constructor(private val notificationsRemoteDataSource: NotificationsRemoteDataSource) :
    NotificationsRepo {

    override suspend fun register(token: String) =
        notificationsRemoteDataSource.register(
            RegisterFCMRequest(
                token
            )
        )

    override suspend fun getNotifications() = notificationsRemoteDataSource.getNotifications()

}