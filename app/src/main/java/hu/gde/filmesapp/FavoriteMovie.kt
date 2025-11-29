package hu.gde.filmesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val director: String,
    val year: Int,
    val runtimeMinutes: Int,
    val description: String?,
    val rating: Double?,
    val genre: String?
)