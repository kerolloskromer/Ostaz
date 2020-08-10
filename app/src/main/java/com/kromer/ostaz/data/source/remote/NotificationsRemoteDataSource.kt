package com.kromer.ostaz.data.source.remote

import com.kromer.ostaz.data.model.RegisterFCMRequest
import javax.inject.Inject


class NotificationsRemoteDataSource @Inject constructor(private val notificationsApiInterface: NotificationsApiInterface) {

    suspend fun register(registerFCMRequest: RegisterFCMRequest) =
        notificationsApiInterface.register(registerFCMRequest)

    suspend fun getNotifications() = notificationsApiInterface.getNotifications()

}