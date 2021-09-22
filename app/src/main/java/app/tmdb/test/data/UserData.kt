package app.tmdb.test.data

import android.annotation.SuppressLint
import app.tmdb.test.utils.PrefsManager
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@SuppressLint("NewApi")
class UserData @Inject constructor(private val preferences: PrefsManager) {

    var guestSessionToken: String? = preferences.getString(GUEST_SESSION_TOKEN)
        set(value) {
            field = value
            preferences.putString(GUEST_SESSION_TOKEN, value)
        }

    var userSessionToken: String? = preferences.getString(USER_SESSION_TOKEN)
        set(value) {
            field = value
            preferences.putString(USER_SESSION_TOKEN, value)
        }

    var expiryDateTime: String? = preferences.getString(SESSION_TOKEN_EXPIRY_TIME)
        set(value) {
            field = value
            preferences.putString(SESSION_TOKEN_EXPIRY_TIME, value)
        }

    var requestToken: String? = preferences.getString(REQUEST_TOKEN)
        set(value) {
            field = value
            preferences.putString(REQUEST_TOKEN, value)
        }

    fun isGuestSessionTokenValid(): Boolean {
        return !guestSessionToken.isNullOrEmpty() && !expiryDateTime.isNullOrEmpty() && hasTokenExpired()
    }

    fun isUserSessionToken(): Boolean {
        return !userSessionToken.isNullOrEmpty()
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

private const val GUEST_SESSION_TOKEN = "GUEST_SESSION_TOKEN"
private const val SESSION_TOKEN_EXPIRY_TIME = "SESSION_TOKEN_EXPIRY_TIME"
private const val USER_SESSION_TOKEN = "USER_SESSION_TOKEN"
const val AUTHENTICATION_GRANTED = "AUTHENTICATION_GRANTED"
const val APPROVED = "APPROVED"
const val REQUEST_TOKEN = "REQUEST_TOKEN"