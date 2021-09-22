package app.tmdb.test.data.network

import app.tmdb.test.data.Result
import app.tmdb.test.data.UserData
import app.tmdb.test.data.model.AccountSessionResponse
import app.tmdb.test.data.model.GuestSessionResponse
import app.tmdb.test.data.model.RequestToken
import app.tmdb.test.data.model.RequestTokenResponse
import app.tmdb.test.utils.Loggable
import retrofit2.await
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val api: Api, private val userData: UserData) : Loggable {

    suspend fun loginAsGuest(): Result<GuestSessionResponse> {
        return try {
            val response = api.createGuestSession().await()
            return if (response.success) {
                Result.Success(response)
            } else {
                Result.Error(Exception("Error creating guest session"))
            }
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun requestToken(): Result<RequestTokenResponse> {
        return try {
            val response = api.getRequestToken().await()
            return if (response.success) {
                log(response.requestToken)
                Result.Success(response)
            } else {
                Result.Error(Exception("Some error retrieving request token"))
            }
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun loginWithAccount(): Result<AccountSessionResponse> {
        return try {
            val response = api.loginWithAccount(RequestToken(userData.requestToken)).await()
            return if (response.success) {
                log(response.sessionId)
                Result.Success(response)
            } else {
                Result.Error(Exception("Some error retrieving session id"))
            }
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}