package hu.gde.filmesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): List<FavoriteMovie>

    @Insert
    fun insertFavorite(movie: FavoriteMovie)

    @Delete
    fun deleteFavorite(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies WHERE title = :title AND year = :year LIMIT 1")
    fun findByTitleAndYear(title: String, year: Int): FavoriteMovie?
}