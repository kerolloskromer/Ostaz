package com.kromer.ostaz.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.kromer.ostaz.data.repo.PostsRepo
import com.kromer.ostaz.ui.base.BaseViewModel
import com.kromer.ostaz.utils.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel @ViewModelInject constructor(private val postsRepo: PostsRepo) :
    BaseViewModel() {

    val posts = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = postsRepo.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}