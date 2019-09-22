package pe.edu.upc.movieup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_favorite.view.*
import pe.edu.upc.movieup.R
import pe.edu.upc.movieup.data.db.AppDatabase
import pe.edu.upc.movieup.data.db.model.Movie

class FavoriteAdapter(val movies: MutableList<Movie>) :
    RecyclerView.Adapter<FavoriteAdapter.PrototypeFavorite>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrototypeFavorite {
        return PrototypeFavorite(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.prototype_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: PrototypeFavorite, position: Int) {
        holder.bindTo(movies[position])
    }

    inner class PrototypeFavorite(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTile = itemView.tvTitle
        private var ibDelete = itemView.ibDelete

        fun bindTo(movie: Movie) {
            tvTile.text = movie.title
            ibDelete.setOnClickListener { view ->

                AppDatabase.getInstance(view.context)
                    .getDao().delete(movie)
                movies.remove(movie)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, movies.size)


            }
        }
    }
}
