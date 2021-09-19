package app.tmdb.test.data.network.interceptors

import app.tmdb.test.utils.Loggable
import okhttp3.Interceptor
import okhttp3.Response

//Get rid of duplication in the Api class
//Can easily manage which Call requires some additional parameters.
//This all is handled in one place.
//It also manages the security

object ApiKeyInterceptor : Interceptor, Loggable {

    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedUrl = chain.request().url().newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        log(modifiedUrl.toString())

        return chain.proceed(chain.request()
            .newBuilder()
            .url(modifiedUrl)
            .build())
    }
}

private const val QUERY_API_KEY = "api_key"
private const val API_KEY = "a9e7c415f821126a55689adb36808458"