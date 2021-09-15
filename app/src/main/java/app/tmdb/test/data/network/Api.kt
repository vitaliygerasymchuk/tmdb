package app.tmdb.test.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("some")
    fun get(): Call<ResponseBody>
}