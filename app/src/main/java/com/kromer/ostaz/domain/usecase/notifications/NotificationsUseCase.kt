package com.kromer.ostaz.domain.usecase.notifications

import com.kromer.ostaz.domain.repository.NotificationsRepo
import javax.inject.Inject

class NotificationsUseCase @Inject constructor(
    private val notificationsRepo: NotificationsRepo
) {
    suspend fun getNotifications() = notificationsRepo.getNotifications()
}