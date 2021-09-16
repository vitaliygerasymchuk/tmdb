package app.tmdb.test.utils

import android.util.Log

interface Loggable {
    fun log(msg: String?) {
        Log.d(this::class.java.simpleName, msg.orEmpty())
    }

    fun logError(msg: String?, throwable: Throwable? = null) {
        Log.e(this::class.java.simpleName, msg.orEmpty(), throwable)
    }
}