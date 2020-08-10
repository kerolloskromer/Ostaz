package com.kromer.ostaz.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.kromer.ostaz.data.local.prefs.Preferences
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: Preferences
}