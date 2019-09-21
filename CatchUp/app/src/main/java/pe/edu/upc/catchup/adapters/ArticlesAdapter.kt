package pe.edu.upc.catchup.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_article.view.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.Article

class ArticlesAdapter(var articles: List<Article>) :
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pictureImageView = itemView.pictureImageView
        val titleTextView = itemView.titleTextView
        val moreButton = itemView.moreButton

        fun bindTo(article: Article) {
            pictureImageView.apply {
                setDefaultImageResId(R.mipmap.ic_launcher)
                setErrorImageResId(R.mipmap.ic_launcher)
                setImageUrl(article.urlToImage)
            }
            titleTextView.text = article.title
            moreButton.setOnClickListener {



            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.ViewHolder, position: Int) {
        holder.bindTo(articles[position])
    }

}