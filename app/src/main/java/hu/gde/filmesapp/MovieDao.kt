package hu.gde.filmesapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): List<FavoriteMovie>

    @Insert
    fun insertFavorite(movie: FavoriteMovie)
}