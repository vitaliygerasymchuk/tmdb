package app.tmdb.test.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tmdb.test.R
import app.tmdb.test.data.LoginRepository
import app.tmdb.test.data.Result
import app.tmdb.test.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userData: UserData, private val repository: LoginRepository) : ViewModel() {

    private val _isSessionActive = MutableLiveData(false)
    val isSessionActive: LiveData<Boolean> = _isSessionActive

    private val _requestToken = MutableLiveData<String>()
    val requestToken: LiveData<String> = _requestToken

    private val _loginError = MutableLiveData<Int>()
    val loginError: LiveData<Int> = _loginError

    fun loginAsGuest() {
        viewModelScope.launch {
            val result = repository.loginAsGuest()

            if (result is Result.Success) {
                userData.sessionToken = result.data.guestSessionId
                userData.expiryDateTime = result.data.expiryDateTime
                _isSessionActive.value = true
            } else {
                _loginError.value = R.string.error_guest_login
            }
        }
    }

    fun loginWithAccount() {
        viewModelScope.launch {
            val result = repository.loginWithAccount()

            if (result is Result.Success) {
                _requestToken.value = result.data.requestToken
            } else {
                _loginError.value = R.string.error_account_login
            }
        }
    }
}
