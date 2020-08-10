package com.kromer.ostaz.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

class Preferences constructor(context: Context) {
    companion object {
        private const val NAME = "OstazCache"
        private const val MODE = Context.MODE_PRIVATE
        private val FIREBASE_ID_TOKEN = Pair("FIREBASE_ID_TOKEN", "")
        private val FIREBASE_TOKEN = Pair("FIREBASE_TOKEN", "")
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firebaseIdToken: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getString(FIREBASE_ID_TOKEN.first, FIREBASE_ID_TOKEN.second)!!
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(FIREBASE_ID_TOKEN.first, value)
        }

    var firebaseToken: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getString(FIREBASE_TOKEN.first, FIREBASE_TOKEN.second)!!
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(FIREBASE_TOKEN.first, value)
        }
}