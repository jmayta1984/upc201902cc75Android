package pe.edu.upc.pokeapi

import com.google.gson.annotations.SerializedName

class PokedexResponse {

    @SerializedName("results")
    var results = ArrayList<Pokemon>()

}
