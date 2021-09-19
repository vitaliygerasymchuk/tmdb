package app.tmdb.test.data

import app.tmdb.test.data.model.GuestSessionResponse
import app.tmdb.test.data.model.RequestTokenResponse
import app.tmdb.test.data.network.LoginRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginRepository @Inject constructor(private val remoteDataSource: LoginRemoteDataSource) {

    suspend fun loginAsGuest(): Result<GuestSessionResponse> = withContext(Dispatchers.IO) {
        return@withContext remoteDataSource.loginAsGuest()
    }

    suspend fun loginWithAccount(): Result<RequestTokenResponse> = withContext(Dispatchers.IO) {
        return@withContext remoteDataSource.loginWithAccount()
    }
}