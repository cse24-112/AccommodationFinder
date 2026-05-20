package com.example.accommodationfinder.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "AccommodationFinderPrefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_USER_EMAIL = "user_email"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, userId: Int, email: String, name: String) {
        val pref = getPreferences(context)
        pref.edit().apply {
            putInt(KEY_USER_ID, userId)
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_NAME, name)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUserId(context: Context): Int {
        return getPreferences(context).getInt(KEY_USER_ID, -1)
    }

    fun getUserEmail(context: Context): String {
        return getPreferences(context).getString(KEY_USER_EMAIL, "") ?: ""
    }

    fun getUserName(context: Context): String {
        return getPreferences(context).getString(KEY_USER_NAME, "") ?: ""
    }

    fun logout(context: Context) {
        val pref = getPreferences(context)
        pref.edit().apply {
            clear()
            apply()
        }
    }
}
