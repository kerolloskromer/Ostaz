package com.kromer.ostaz.ui.main.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import com.kromer.ostaz.data.repo.PostsRepo
import com.kromer.ostaz.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import kotlin.coroutines.CoroutineContext

class DashboardViewModel @ViewModelInject constructor(private val postsRepo: PostsRepo) :
    BaseViewModel() {

    //create a new Job
    private val parentJob = Job()

    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.IO

    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    fun uploadPost(text: String, file: File) {
        scope.launch {
            val textPart: RequestBody = text.toRequestBody("text/plain".toMediaTypeOrNull())

//            val filePart: RequestBody = file.asRequestBody("audio/*".toMediaTypeOrNull())

            val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                file.asRequestBody("audio/*".toMediaTypeOrNull())
            )

            postsRepo.uploadPost(textPart, filePart)
        }
    }
}