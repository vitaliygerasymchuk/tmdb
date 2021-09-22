package app.tmdb.test.data.network

import app.tmdb.test.data.model.AccountSessionResponse
import app.tmdb.test.data.model.GuestSessionResponse
import app.tmdb.test.data.model.RequestToken
import app.tmdb.test.data.model.RequestTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("authentication/guest_session/new")
    fun createGuestSession(): Call<GuestSessionResponse>

    @GET("authentication/token/new")
    fun getRequestToken(): Call<RequestTokenResponse>

    @POST("authentication/session/new")
    fun loginWithAccount(@Body requestToken: RequestToken): Call<AccountSessionResponse>
}
// TODO: 9/22/2021 Chaining requestBody using Interceptors ?