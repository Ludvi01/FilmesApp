package hu.gde.filmesapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra("movie") as? Movie

        val textTitle: TextView = findViewById(R.id.textDetailTitle)
        val textDirector: TextView = findViewById(R.id.textDetailDirector)
        val textYearRuntime: TextView = findViewById(R.id.textDetailYearRuntime)
        val textGenre: TextView = findViewById(R.id.textDetailGenre)
        val textRating: TextView = findViewById(R.id.textDetailRating)
        val textDescription: TextView = findViewById(R.id.textDetailDescription)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        if (movie != null) {
            textTitle.text = movie.title
            textDirector.text = movie.director
            textYearRuntime.text = "${movie.year} • ${movie.runtimeMinutes} perc"
            textGenre.text = "Műfaj: ${movie.genre ?: "-"}"
            textRating.text = "Értékelés: ${movie.rating ?: "-"} /10"
            textDescription.text = movie.description ?: "Nincs leírás."
        }

        // Mentés gomb
        buttonSave.setOnClickListener {
        }
    }
}