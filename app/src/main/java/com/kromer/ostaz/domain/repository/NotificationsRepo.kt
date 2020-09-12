package com.kromer.ostaz.domain.repository

import com.kromer.ostaz.data.model.NotificationObj


interface NotificationsRepo {

    suspend fun register(token: String)

    suspend fun getNotifications(): List<NotificationObj>
}