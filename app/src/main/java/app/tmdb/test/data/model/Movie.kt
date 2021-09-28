package app.tmdb.test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(@PrimaryKey var id: Int, var name: String)
