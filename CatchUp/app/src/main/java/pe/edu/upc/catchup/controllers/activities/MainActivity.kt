package pe.edu.upc.catchup.controllers.activities

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.controllers.fragments.FavoritesFragment
import pe.edu.upc.catchup.controllers.fragments.HomeFragment
import pe.edu.upc.catchup.controllers.fragments.SettingsFragment
import pe.edu.upc.catchup.controllers.fragments.SourcesFragment
import pe.edu.upc.catchup.network.NewsApi

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigateTo(navView.menu.findItem(R.id.navigation_home))
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when(item.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_sources -> SourcesFragment()
            R.id.navigation_favorites -> FavoritesFragment()
            R.id.navigation_settings -> SettingsFragment()
            else -> HomeFragment()
        }
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.isChecked = true

        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, getFragmentFor(item))
            .commit() > 0
    }
}
