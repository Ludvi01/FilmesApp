package hu.gde.filmesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var favoriteKeys: Set<String> = emptySet()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDirector: TextView = itemView.findViewById(R.id.textDirector)
        val textYearAndLength: TextView = itemView.findViewById(R.id.textYearAndLength)
        val textFavoriteStar: TextView = itemView.findViewById(R.id.textFavoriteStar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.textTitle.text = movie.title
        holder.textDirector.text = movie.director
        holder.textYearAndLength.text = "${movie.year} • ${movie.duration} ${holder.itemView.context.getString(R.string.minutes)}"

        val key = "${movie.title}_${movie.year}"

        if (favoriteKeys.contains(key)) {
            holder.textFavoriteStar.visibility = View.VISIBLE
        } else {
            holder.textFavoriteStar.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    fun updateFavorites(keys: Set<String>) {
        favoriteKeys = keys
        notifyDataSetChanged()
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}