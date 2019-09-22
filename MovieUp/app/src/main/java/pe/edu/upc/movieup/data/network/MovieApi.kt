package pe.edu.upc.movieup.data.network

import pe.edu.upc.movieup.data.network.model.MovieApiResponse
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie")
    fun getSearchMovies(@Query("api_key") apiKey: String, @Query("query") tile: String): Call<MovieApiResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/search/"

        fun create(): MovieApi {

            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieApi::class.java)
        }
    }
}