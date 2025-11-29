package hu.gde.filmesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var movieDao: MovieDao
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val db = AppDatabase.getInstance(applicationContext)
        movieDao = db.movieDao()

        recyclerView = findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val favorites = movieDao.getAllFavorites()

        val movies = favorites.map { fav ->
            Movie(
                title = fav.title,
                director = fav.director,
                year = fav.year,
                runtimeMinutes = fav.runtimeMinutes,
                description = fav.description,
                rating = fav.rating,
                genre = fav.genre
            )
        }

        adapter = MovieAdapter(movies) { movie ->
            // kedvencekből részletes nézet
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}