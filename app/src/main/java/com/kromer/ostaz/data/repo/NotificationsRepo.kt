package com.kromer.ostaz.data.repo

import com.kromer.ostaz.data.model.RegisterFCMRequest
import com.kromer.ostaz.data.source.remote.NotificationsRemoteDataSource
import javax.inject.Inject


class NotificationsRepo @Inject constructor(private val notificationsRemoteDataSource: NotificationsRemoteDataSource) {

    suspend fun register(token: String) =
        notificationsRemoteDataSource.register(RegisterFCMRequest(token))

    suspend fun getNotifications() = notificationsRemoteDataSource.getNotifications()

}