package pe.edu.upc.catchup.controllers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import pe.edu.upc.catchup.R

import kotlinx.android.synthetic.main.activity_source.*
import kotlinx.android.synthetic.main.content_source.*
import pe.edu.upc.catchup.models.Favorite
import pe.edu.upc.catchup.models.Source

class SourceActivity : AppCompatActivity() {
    var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.extras?.apply {
            val source = getSerializable("source") as Source
            logoImageView.apply {
                setDefaultImageResId(R.mipmap.ic_launcher)
                setErrorImageResId(R.mipmap.ic_launcher)
                setImageUrl(source.urlToLogo)
            }
            nameTextView.text = source.name
            descriptionTextView.text = source.description
            isFavorite = source.isFavorite
            favoriteButton.setImageResource(Favorite.resourceForState(isFavorite) )
            favoriteButton.setOnClickListener {
                isFavorite = !isFavorite
                favoriteButton.setImageResource(Favorite.resourceForState(isFavorite) )
                source.isFavorite = isFavorite

            }
        }
    }



}
