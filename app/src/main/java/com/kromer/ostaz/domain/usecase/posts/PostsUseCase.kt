package com.kromer.ostaz.domain.usecase.posts

import com.kromer.ostaz.domain.repository.PostsRepo
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    private val postsRepo: PostsRepo
) {
    suspend fun getPosts() = postsRepo.getPosts()
}