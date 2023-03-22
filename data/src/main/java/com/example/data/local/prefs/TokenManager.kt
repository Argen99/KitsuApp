package com.example.data.local.prefs

import android.content.Context

class TokenManager(context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
    var accessToken: String?
        get() = prefs.getString(USER_ACCESS_TOKEN, null)
        set(value) = prefs.edit().putString(USER_ACCESS_TOKEN, value).apply()


    var refreshToken: String?
        get() = prefs.getString(USER_REFRESH_TOKEN, null)
        set(value) = prefs.edit().putString(USER_REFRESH_TOKEN, value).apply()


    fun deleteAccessToken() {
        prefs.edit().remove(USER_ACCESS_TOKEN).apply()
    }

    fun deleteRefreshToken() {
        prefs.edit().remove(USER_REFRESH_TOKEN).apply()
    }

    fun clearPrefs() {
        prefs.edit().clear().apply()
    }

    companion object {
        const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
        const val USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN"
        const val USER_REFRESH_TOKEN = "USER_REFRESH_TOKEN"
    }
}