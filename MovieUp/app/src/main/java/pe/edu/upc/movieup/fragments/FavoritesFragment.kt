package pe.edu.upc.movieup.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import pe.edu.upc.movieup.R
import pe.edu.upc.movieup.adapters.FavoriteAdapter
import pe.edu.upc.movieup.data.db.AppDatabase
import pe.edu.upc.movieup.data.db.model.Movie

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    lateinit var favoriteAdapter: FavoriteAdapter
    var movies: MutableList<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavorite.layoutManager = LinearLayoutManager(context)
        loadFavorites()
    }

    private fun loadFavorites() {

        movies = AppDatabase.getInstance(view!!.context).getDao().getAll()
        favoriteAdapter = FavoriteAdapter(movies)
        rvFavorite.adapter = favoriteAdapter
    }
}
