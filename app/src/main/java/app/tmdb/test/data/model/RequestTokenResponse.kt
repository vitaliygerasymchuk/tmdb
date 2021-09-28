package app.tmdb.test.data.model

import com.squareup.moshi.Json

data class RequestTokenResponse(
    @field:Json(name = "success") var success: Boolean,
    @field:Json(name = "request_token") var requestToken: String,
    @field:Json(name = "expires_at") var expiryDateTime: String,
)