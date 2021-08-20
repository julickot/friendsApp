package com.julickot.friendsapp.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

interface Settings {

    fun isFirstStart(): Boolean

    fun setStarted()

}

class SettingsImpl(private val app: Application): Settings {
    override fun isFirstStart(): Boolean {
        val pref = getPref()
        return (pref.getBoolean("first", true))
    }

    override fun setStarted() {
        val pref = getPref()
        with (pref.edit()) {
            putBoolean("first", false)
            apply()
        }
    }

    private fun getPref(): SharedPreferences {
        return app.getSharedPreferences("app", Context.MODE_PRIVATE)
    }
}