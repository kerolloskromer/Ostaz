package com.kromer.ostaz.domain.usecase.posts

import com.kromer.ostaz.domain.repository.PostsRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UploadPostUseCase @Inject constructor(
    private val postsRepo: PostsRepo
) {
    suspend fun uploadPost(
        textPart: RequestBody,
        filePart: MultipartBody.Part
    ) = postsRepo.uploadPost(textPart, filePart)
}