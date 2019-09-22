package pe.edu.upc.movieup.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import pe.edu.upc.movieup.R
import pe.edu.upc.movieup.adapters.MovieAdapter
import pe.edu.upc.movieup.data.db.model.Movie
import pe.edu.upc.movieup.data.network.MovieApi
import pe.edu.upc.movieup.data.network.model.MovieApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    lateinit var movieAdapter: MovieAdapter
    var movies: MutableList<Movie> = ArrayList()

    companion object {
        private const val ERROR_TAG = "Error"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovie.layoutManager = LinearLayoutManager(context)

        btSearch.setOnClickListener {
            searchMovies()
        }
    }

    private fun searchMovies() {
        val apiKey = "3cae426b920b29ed2fb1c0749f258325"
        val query = etSearch.text.toString()
        val retrofit = MovieApi.create().getSearchMovies(apiKey, query)

        retrofit.enqueue(object : Callback<MovieApiResponse> {
            override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                Log.d(ERROR_TAG, t.toString())
            }

            override fun onResponse(
                call: Call<MovieApiResponse>,
                response: Response<MovieApiResponse>
            ) {
                if (response.isSuccessful) {
                    movies = response.body()!!.results
                    movieAdapter = MovieAdapter(movies)
                    rvMovie.adapter = movieAdapter

                }
            }
        })


    }
}
