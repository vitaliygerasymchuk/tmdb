package app.tmdb.test.utils

import android.content.Context
import javax.inject.Inject

class PrefsManager @Inject constructor(context: Context) {

    private val prefs = context.getSharedPreferences("tmdb", Context.MODE_PRIVATE)

    fun getString(name: String) = prefs.getString(name, null);

    fun putString(name: String, string: String?) {
        prefs.edit().putString(name, string).apply()
    }
}