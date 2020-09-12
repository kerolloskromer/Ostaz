package com.kromer.ostaz.domain.usecase.notifications

import com.kromer.ostaz.domain.repository.NotificationsRepo
import javax.inject.Inject

class RegisterFirebaseUseCase @Inject constructor(
    private val notificationsRepo: NotificationsRepo
) {
    suspend fun register(token: String) = notificationsRepo.register(token)
}