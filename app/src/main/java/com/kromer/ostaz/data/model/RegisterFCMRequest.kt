package com.kromer.ostaz.data.model

import com.google.gson.annotations.SerializedName

data class RegisterFCMRequest(@SerializedName("token") val token: String)