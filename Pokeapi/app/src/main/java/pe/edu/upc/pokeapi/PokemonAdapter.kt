package pe.edu.upc.pokeapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_pokemon.view.*

class PokemonAdapter(var pokemons: ArrayList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonPrototype>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonPrototype {
        return PokemonPrototype(
            LayoutInflater.from(parent.context).inflate(
                R.layout.prototype_pokemon,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonPrototype, position: Int) {
        holder.tvName.text = pokemons.get(position).name
    }

    class PokemonPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPokemon = itemView.ivPokemon
        var tvName = itemView.tvName


    }

}
