package com.example.data.local.prefs

import android.content.Context

class UserDataPrefs(context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveCurrentUser(login: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(CURRENT_USER, login)
        editor.apply()
    }

    fun currentUser(): Boolean {
        return prefs.getBoolean(CURRENT_USER, false)
    }

    companion object {
        const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"
        const val CURRENT_USER = "PREFS_TOKEN_FILE"
    }
}