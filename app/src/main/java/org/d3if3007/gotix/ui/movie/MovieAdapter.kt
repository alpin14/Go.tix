package org.d3if3007.gotix.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3007.gotix.databinding.MovieListBinding
import org.d3if3007.gotix.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val dataFilm = mutableListOf<Movie>()

    fun updateData(newData: List<Movie>) {
        dataFilm.clear()
        dataFilm.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: MovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            namaFilmTextView.text = movie.nama
            jenisTextView.text = movie.genre
            imageView.setImageResource(movie.imageResId)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataFilm.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataFilm[position])
    }
}