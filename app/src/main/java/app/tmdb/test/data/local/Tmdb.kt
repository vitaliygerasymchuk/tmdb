package app.tmdb.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.tmdb.test.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = true)
abstract class Tmdb : RoomDatabase() {
}