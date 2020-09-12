package com.kromer.ostaz.data.repositoryimpl

import com.kromer.ostaz.data.source.local.PostsLocalDataSource
import com.kromer.ostaz.data.source.remote.PostsRemoteDataSource
import com.kromer.ostaz.domain.repository.PostsRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val postsRemoteDataSource: PostsRemoteDataSource,
    private val postsLocalDataSource: PostsLocalDataSource
) : PostsRepo {

    override suspend fun getPosts() = postsRemoteDataSource.getPosts()

    override suspend fun uploadPost(
        textPart: RequestBody,
        filePart: MultipartBody.Part
    ) = postsRemoteDataSource.uploadPost(textPart, filePart)
}