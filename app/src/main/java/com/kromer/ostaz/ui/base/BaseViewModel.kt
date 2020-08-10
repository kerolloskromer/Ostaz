package com.kromer.ostaz.ui.base

import androidx.lifecycle.ViewModel
import com.kromer.ostaz.data.local.prefs.Preferences
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var preferences: Preferences
}