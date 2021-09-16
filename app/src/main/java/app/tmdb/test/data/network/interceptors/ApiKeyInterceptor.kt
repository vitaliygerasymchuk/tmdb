package app.tmdb.test.data.network.interceptors

import app.tmdb.test.data.UserData
import app.tmdb.test.utils.Loggable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

//Get rid of duplication in the Api class
//Can easily manage which Call requires some additional parameters.
//This all is handled in one place.
//It also manages the security

@Singleton
class ApiKeyInterceptor @Inject constructor(
    private val userData: UserData
) : Interceptor, Loggable {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request() // retrieving the original request from Chain
        val originalUrl = originalRequest.url() // modifying the URL
        log("intercept: originalUrl $originalUrl")

        val modifiedUrl =
            originalUrl.newBuilder()
                .addQueryParameter(QUERY_API_KEY, userData.selectedApiKey)
                .build()
        log("intercept: originalUrl $originalUrl")

        val modifiedRequest = originalRequest
            .newBuilder()
//            .addHeader(HEADER_BEARER, "$AUTH ${userData.sessionToken}")
            .url(modifiedUrl)
            .build()

        return chain.proceed(
            modifiedRequest
        ) // providing the modified request to the chain.
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val HEADER_BEARER = "Bearer"
        private const val AUTH = "Authorization"
    }
}