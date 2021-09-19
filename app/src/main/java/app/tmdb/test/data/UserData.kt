package app.tmdb.test.data

import android.annotation.SuppressLint
import app.tmdb.test.utils.PrefsManager
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@SuppressLint("NewApi")
class UserData @Inject constructor(private val preferences: PrefsManager) {

    var sessionToken: String? = preferences.getString(SESSION_TOKEN)
        set(value) {
            field = value
            preferences.putString(SESSION_TOKEN, value)
        }

    var expiryDateTime: String? = preferences.getString(SESSION_TOKEN_EXPIRY_TIME)
        set(value) {
            field = value
            preferences.putString(SESSION_TOKEN_EXPIRY_TIME, value)
        }

    fun isSessionTokenValid(): Boolean {
        return !sessionToken.isNullOrEmpty() && !expiryDateTime.isNullOrEmpty() && hasTokenExpired()
    }

    private fun hasTokenExpired(): Boolean {
        val expiryDateTime = LocalDateTime.parse(expiryDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'"))
        val zonedDateTime = LocalDateTime.now(ZoneOffset.UTC)
        val currentDateTime = zonedDateTime.run {
            LocalDateTime.of(year, month, dayOfMonth, hour, minute, second)
        }

        return currentDateTime.isBefore(expiryDateTime)
    }
}

private const val SESSION_TOKEN = "SESSION_TOKEN"
private const val SESSION_TOKEN_EXPIRY_TIME = "SESSION_TOKEN_EXPIRY_TIME"