package com.kromer.ostaz.data.source.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostsRemoteDataSource @Inject constructor(private val postsApiInterface: PostsApiInterface) {

    suspend fun getPosts() = postsApiInterface.getPosts()

    suspend fun uploadPost(
        textPart: RequestBody,
        filePart: MultipartBody.Part
    ) = postsApiInterface.uploadPost(textPart, filePart)
}