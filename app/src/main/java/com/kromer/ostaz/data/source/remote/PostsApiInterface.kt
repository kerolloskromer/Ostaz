package com.kromer.ostaz.data.source.remote

import com.kromer.ostaz.data.model.Post
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PostsApiInterface {

    @GET("posts/")
    suspend fun getPosts(): List<Post>

    @Multipart
    @POST("posts/")
    suspend fun uploadPost(
        @Part("text") textPart: RequestBody,
        @Part filePart: MultipartBody.Part
    ): Post
}