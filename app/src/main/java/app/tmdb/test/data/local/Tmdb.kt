package app.tmdb.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = true)
abstract class Tmdb : RoomDatabase() {
}