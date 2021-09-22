package app.tmdb.test.ui.home

import androidx.lifecycle.ViewModel
import app.tmdb.test.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userData: UserData) : ViewModel() {

    fun isSessionValid(): Boolean = userData.isUserSessionToken()

    fun isGuestSessionValid(): Boolean = userData.isGuestSessionTokenValid()

}