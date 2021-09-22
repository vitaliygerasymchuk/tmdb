package app.tmdb.test.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import app.tmdb.test.R
import app.tmdb.test.data.APPROVED
import app.tmdb.test.data.AUTHENTICATION_GRANTED
import app.tmdb.test.data.REQUEST_TOKEN
import app.tmdb.test.utils.Loggable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Loggable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val result = intent?.data
        val wasUserAuthenticated = result?.getQueryParameter("approved")
        val requestToken = result?.getQueryParameter("request_token")

        wasUserAuthenticated?.let {
            if (it == "true") {
                supportFragmentManager.setFragmentResult(AUTHENTICATION_GRANTED,
                    bundleOf(
                        APPROVED to true,
                        REQUEST_TOKEN to requestToken
                    ))
            }
        } ?: log("Error Authenticating user")
    }
}

