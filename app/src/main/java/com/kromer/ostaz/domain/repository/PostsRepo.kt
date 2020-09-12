package com.kromer.ostaz.domain.repository

import com.kromer.ostaz.domain.entity.Post
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PostsRepo {

    suspend fun getPosts(): List<Post>

    suspend fun uploadPost(
        textPart: RequestBody,
        filePart: MultipartBody.Part
    ): Post
}