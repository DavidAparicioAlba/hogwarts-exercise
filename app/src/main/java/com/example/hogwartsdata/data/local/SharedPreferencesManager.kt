package com.example.hogwartsdata.data.local

import android.app.Activity
import android.content.Context

class SharedPreferencesManager(val context: Context) {

    private val sharedPreferences by lazy { context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE) }
    private val editor by lazy { sharedPreferences.edit() }

    companion object {
        private const val PREF_NAME = "kotlin_preferences"

        private const val USER_KEY = "user"
        private const val FAVOURITES_KEY = "favourites"

    }

    fun getUser(): String? {
        return sharedPreferences.getString(USER_KEY, "")
    }

    fun setUser(token: String) {
        editor.putString(USER_KEY, token).apply()
    }
    fun getFavourites(): MutableSet<String> {
        return sharedPreferences.getStringSet(FAVOURITES_KEY, mutableSetOf()) as MutableSet<String>
    }
    fun setFavourites(favs: MutableSet<String>) {
        editor.putStringSet(FAVOURITES_KEY, favs).apply()
    }


}