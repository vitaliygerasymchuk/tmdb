package app.tmdb.test.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/authentication/guest_session/new")
    fun createGuestSession(): Call<ResponseBody>

    @GET("authentication/token/new")
    fun getRequestToken(): Call<RequestTokenResponse>

    ///Authenticate user via TMD web URL:

//    https://www.themoviedb.org/authenticate/REQUEST_TOKEN
}