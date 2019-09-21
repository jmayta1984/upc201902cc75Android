package pe.edu.upc.pokeapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PokemonAdapter
    var pokemons = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getpokemons()


        rvPokemon.layoutManager = GridLayoutManager(this, 3, RecyclerView.HORIZONTAL, true)
        //LinearLayoutManager(this)

    }

    private fun getpokemons() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonInterface = retrofit.create(PokemonInterface::class.java)

        val methodPokemonsData: Call<PokedexResponse> = pokemonInterface.getPokemonsData()

        methodPokemonsData.enqueue(object : Callback<PokedexResponse> {
            override fun onFailure(call: Call<PokedexResponse>, t: Throwable) {
                Log.d("Excepción: ", t.toString())
            }

            override fun onResponse(
                call: Call<PokedexResponse>,
                response: Response<PokedexResponse>
            ) {

                if (response.isSuccessful) {
                    pokemons = response.body()!!.results
                    adapter = PokemonAdapter(pokemons)
                    rvPokemon.adapter = adapter
                }

            }

        })

    }
}
