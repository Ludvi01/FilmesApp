package hu.gde.filmesapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    // Activity létrehozása
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MovieAdapter(emptyList()) { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        loadMovies()

    }

    // Menü létrehozása
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Menü elemek
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorites -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // filmek betöltése
    private fun loadMovies() {
        RetrofitClient.api.getMovies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(
                call: Call<List<Movie>>,
                response: Response<List<Movie>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val movies = response.body()!!
                    adapter.updateMovies(movies)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Nem sikerült letölteni a filmeket!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Hálózati hiba: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}