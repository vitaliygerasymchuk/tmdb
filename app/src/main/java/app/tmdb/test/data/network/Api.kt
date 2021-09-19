package app.tmdb.test.data.network

import app.tmdb.test.data.model.GuestSessionResponse
import app.tmdb.test.data.model.RequestTokenResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("authentication/guest_session/new")
    fun createGuestSession(): Call<GuestSessionResponse>

    @GET("authentication/token/new")
    fun getRequestToken(): Call<RequestTokenResponse>

    ///Authenticate user via TMD web URL:
    ///https://www.themoviedb.org/authenticate/REQUEST_TOKEN
}