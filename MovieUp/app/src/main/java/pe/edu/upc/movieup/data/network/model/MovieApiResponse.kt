package pe.edu.upc.movieup.data.network.model

import com.google.gson.annotations.SerializedName
import pe.edu.upc.movieup.data.db.model.Movie

class MovieApiResponse(

    @SerializedName("results")
    val results: MutableList<Movie>

)
