package com.kromer.ostaz.presentation.ui.main.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import com.kromer.ostaz.domain.usecase.notifications.NotificationsUseCase
import com.kromer.ostaz.domain.usecase.notifications.RegisterFirebaseUseCase
import com.kromer.ostaz.presentation.base.BaseViewModel
import com.kromer.ostaz.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NotificationsViewModel @ViewModelInject constructor(
    private val registerFirebaseUseCase: RegisterFirebaseUseCase,
    private val notificationsUseCase: NotificationsUseCase
) :
    BaseViewModel() {

    //create a new Job
    private val parentJob = Job()

    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.IO

    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    fun register(token: String) {
        scope.launch {
            registerFirebaseUseCase.register(token)
        }
    }

    val notifications = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = notificationsUseCase.getNotifications()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}