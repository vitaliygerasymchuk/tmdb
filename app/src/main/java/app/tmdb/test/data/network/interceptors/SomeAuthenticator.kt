package app.tmdb.test.data.network.interceptors

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class SomeAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        //refresh the expired token.
        //repeat the failed request.

        // val response = api.fireSomeCall.execute
        // if response.isOk { } else { }
        return null
    }
}