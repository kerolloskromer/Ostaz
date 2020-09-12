package com.kromer.ostaz.presentation.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.kromer.ostaz.domain.usecase.posts.PostsUseCase
import com.kromer.ostaz.presentation.base.BaseViewModel
import com.kromer.ostaz.utils.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel @ViewModelInject constructor(private val postsUseCase: PostsUseCase) :
    BaseViewModel() {

    val posts = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = postsUseCase.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}