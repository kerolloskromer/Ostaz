package com.kromer.ostaz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("file")
    val filePath: String?,
    var isPLaying: Boolean
)