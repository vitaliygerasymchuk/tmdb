package app.tmdb.test.data.model

import com.squareup.moshi.Json

data class GuestSessionResponse(
    @field:Json(name = "success") var success: Boolean,
    @field:Json(name = "guest_session_id") var guestSessionId: String,
    @field:Json(name = "expires_at") var expiryDateTime: String,
)
