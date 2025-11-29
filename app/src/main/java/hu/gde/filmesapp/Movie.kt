package hu.gde.filmesapp

import java.io.Serializable

data class Movie(
    val title: String,
    val director: String,
    val year: Int,
    val duration: Int,
    val description: String? = null,
    val rating: Double? = null,
    val genre: String? = null
) : Serializable