package pe.edu.upc.catchup.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_source.view.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.controllers.activities.SourceActivity
import pe.edu.upc.catchup.models.Favorite
import pe.edu.upc.catchup.models.Source
import java.io.Serializable

class SourcesAdapter(var sources: List<Source>) :
    RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    var selectedIndex = 0
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        val itemSource = itemView.itemSource
        val logoImageView = itemView.logoImageView
        val favoriteImageView = itemView.favoriteImageView
        val nameTextView = itemView.nameTextView

        fun bindTo(source: Source) {
            logoImageView.apply {
                setDefaultImageResId(R.mipmap.ic_launcher)
                setErrorImageResId(R.mipmap.ic_launcher)
                Log.d("NewsApi", source.urlToLogo)
                setImageUrl(source.urlToLogo)
            }
            favoriteImageView.setImageResource(Favorite.resourceForState(source.isFavorite))
            nameTextView.text = source.name
            itemSource.setOnClickListener {
                this@SourcesAdapter.selectedIndex = adapterPosition
                val intent = Intent(it.context, SourceActivity::class.java)
                intent.putExtra("source", source as Serializable)
                it.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_source, parent, false))
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun onBindViewHolder(holder: SourcesAdapter.ViewHolder, position: Int) {
        holder.bindTo(sources.get(position))
    }

    fun verifyItemChanged() {
        notifyItemChanged(selectedIndex)

    }
}