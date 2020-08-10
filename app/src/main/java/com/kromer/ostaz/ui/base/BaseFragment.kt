package com.kromer.ostaz.ui.base

import androidx.fragment.app.Fragment
import com.kromer.ostaz.data.local.prefs.Preferences
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var preferences: Preferences
}