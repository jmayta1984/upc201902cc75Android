package pe.edu.upc.movieup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.prototype_movie.view.*
import pe.edu.upc.movieup.R
import pe.edu.upc.movieup.data.db.AppDatabase
import pe.edu.upc.movieup.data.db.model.Movie

class MovieAdapter(val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.PrototypeMovie>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrototypeMovie {
        return PrototypeMovie(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.prototype_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: PrototypeMovie, position: Int) {
        holder.bindTo(movies[position])
    }

    inner class PrototypeMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.tvTitle
        private val tvOverview = itemView.tvOverview
        private val ivPoster = itemView.ivPoster
        private val ibFavorite = itemView.ibFavorite

        fun bindTo(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            Glide.with(itemView).load(movie.urlToPoster).into(ivPoster)

            ibFavorite.setOnClickListener { view ->

                AppDatabase.getInstance(view.context)
                    .getDao().insert(movie)
                movies.remove(movie)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, movies.size)


            }


        }

    }

}


