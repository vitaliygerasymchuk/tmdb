package app.tmdb.test.data.model

import com.squareup.moshi.Json

data class RequestToken(
    @field: Json(name = "request_token") var requestToken: String? = null,
)
