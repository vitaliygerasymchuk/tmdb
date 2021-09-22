package app.tmdb.test.data.model

import com.squareup.moshi.Json

data class AccountSessionResponse(
    @field:Json(name = "success") var success: Boolean,
    @field:Json(name = "session_id") var sessionId: String,
)
