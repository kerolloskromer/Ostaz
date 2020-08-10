package com.kromer.ostaz.data.source.remote

import com.kromer.ostaz.data.model.NotificationObj
import com.kromer.ostaz.data.model.RegisterFCMRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationsApiInterface {

    @POST("notifications/register/")
    suspend fun register(@Body registerFCMRequest: RegisterFCMRequest)

    @GET("notifications/")
    suspend fun getNotifications(): List<NotificationObj>
}