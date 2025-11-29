package hu.gde.filmesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // teszt adatok – később jön majd helyette a JSON
        val movies = listOf(
            Movie("The Matrix", "Lana Wachowski, Lilly Wachowski", 1999, 136),
            Movie("Inception", "Christopher Nolan", 2010, 148),
            Movie("Interstellar", "Christopher Nolan", 2014, 169)
        )

        adapter = MovieAdapter(movies)
        recyclerView.adapter = adapter
    }
}