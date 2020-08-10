package com.kromer.ostaz.ui.main.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.kromer.ostaz.data.repo.NotificationsRepo
import com.kromer.ostaz.ui.base.BaseViewModel
import com.kromer.ostaz.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NotificationsViewModel @ViewModelInject constructor(private val notificationsRepo: NotificationsRepo) :
    BaseViewModel() {

    //create a new Job
    private val parentJob = Job()

    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.IO

    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    fun register(token: String) {
        scope.launch {
            notificationsRepo.register(token)
        }
    }

    val notifications = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = notificationsRepo.getNotifications()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}