package hu.gde.filmesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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