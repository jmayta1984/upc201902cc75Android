package pe.edu.upc.pokeapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonInterface {

    @GET("pokemon")
    fun getPokemonsData(): Call<PokedexResponse>

    @GET("pokemon?")
    fun getPokemonsRangeData(
        @Query("offset") offSet: Int,
        @Query("limit") limit: Int
    ): Call<PokedexResponse>

    @GET("pokemon/{id}")
    fun getPokemonData(@Path("id") id: String): Call<PokemonResponse>
}