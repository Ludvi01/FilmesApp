package hu.gde.filmesapp

import retrofit2.Call
import retrofit2.http.GET

interface MoviesApiService {

    @GET("movies.json")
    fun getMovies(): Call<List<Movie>>
}