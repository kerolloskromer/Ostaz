package com.kromer.ostaz.data.repo

import com.kromer.ostaz.data.source.remote.PostsRemoteDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostsRepo @Inject constructor(private val postsRemoteDataSource: PostsRemoteDataSource) {

    suspend fun getPosts() = postsRemoteDataSource.getPosts()

    suspend fun uploadPost(
        textPart: RequestBody,
        filePart: MultipartBody.Part
    ) = postsRemoteDataSource.uploadPost(textPart, filePart)
}