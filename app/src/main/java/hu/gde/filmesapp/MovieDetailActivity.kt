package hu.gde.filmesapp

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDao: MovieDao
    private var movie: Movie? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Room DB + DAO
        val db = AppDatabase.getInstance(applicationContext)
        movieDao = db.movieDao()

        // A movie értékét áthelyezzük egy külön property-be.
        movie = intent.getSerializableExtra("movie", Movie::class.java)

        // Részletek nézeteinek lekérése
        val textTitle: TextView = findViewById(R.id.textDetailTitle)
        val textDirector: TextView = findViewById(R.id.textDetailDirector)
        val textYearRuntime: TextView = findViewById(R.id.textDetailYearRuntime)
        val textGenre: TextView = findViewById(R.id.textDetailGenre)
        val textRating: TextView = findViewById(R.id.textDetailRating)
        val textDescription: TextView = findViewById(R.id.textDetailDescription)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        movie?.let { m ->
            textTitle.text = m.title
            textDirector.text = m.director
            textYearRuntime.text = "${m.year} • ${m.duration} perc"
            textGenre.text = "Műfaj: ${m.genre ?: "-"}"
            textRating.text = "Értékelés: ${m.rating ?: "-"} /10"
            textDescription.text = m.description ?: "Nincs leírás."
        }

        // Mentés gomb
        buttonSave.setOnClickListener {
            movie?.let { m ->

                // létezik‑e már
                val existing = movieDao.findByTitleAndYear(m.title, m.year)

                if (existing != null) {
                    // szerepel egyszer
                    Toast.makeText(
                        this,
                        "Korábban már hozzáadtad a kedvencekhez.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // mentés
                    val favorite = FavoriteMovie(
                        title = m.title,
                        director = m.director,
                        year = m.year,
                        duration = m.duration,
                        description = m.description,
                        rating = m.rating,
                        genre = m.genre
                    )
                    movieDao.insertFavorite(favorite)
                    Toast.makeText(
                        this,
                        "Sikeresen hozzáadtad a kedvencekhez!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}