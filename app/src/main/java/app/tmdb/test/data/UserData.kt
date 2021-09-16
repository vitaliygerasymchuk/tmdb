package app.tmdb.test.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserData @Inject constructor(
    @ApplicationContext context: Context
) {
    var selectedApiKey: String = "whatever"
        set(value) {
            field = value
            // store to the SharePrefs
        }
        get() = "whatever" // retrieve from the Prefs

    var sessionToken: String = "whateever"
        set(value) {
            field = value
            // store to the SharePrefs
        }
        get() = "whatever" // retrieve from the Prefs
}