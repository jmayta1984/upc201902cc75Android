package pe.edu.upc.movieup.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.movieup.R
import pe.edu.upc.movieup.fragments.FavoritesFragment
import pe.edu.upc.movieup.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateTo(bnMain.menu.findItem(R.id.navigation_search))

        bnMain.setOnNavigationItemSelectedListener { item ->
            navigateTo(item)
            true

        }
    }

    private fun navigateTo(item: MenuItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContent, fragmentFor(item))
            .commit()
    }

    private fun fragmentFor(item: MenuItem): Fragment {
        return when (item.itemId) {
            R.id.navigation_search -> SearchFragment()
            R.id.navigation_favorites -> FavoritesFragment()
            else -> SearchFragment()
        }

    }
}
