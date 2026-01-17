package com.vaudibert.canidrive.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.AppBarLayout
import com.vaudibert.canidrive.R
import com.vaudibert.canidrive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val time = CanIDrive.instance.time

    override fun onCreate(savedInstanceState: Bundle?) {
        when {
            time.isSaintPatrick() -> setTheme(R.style.AppThemeSaintPatrick)
            else -> setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
            .addOnDestinationChangedListener { _, destination, _ ->
                binding.appBarDrinker.visibility = if (destination.id == R.id.splashFragment)
                    AppBarLayout.GONE
                else
                    AppBarLayout.VISIBLE
                binding.toolbar.title = when (destination.id) {
                    R.id.driveFragment -> getString(R.string.can_i_drive_question)
                    R.id.drinkerFragment -> getString(R.string.about_you)
                    R.id.addDrinkFragment -> getString(R.string.select_a_drink)
                    R.id.addPresetFragment -> getString(R.string.add_preset_description)
                    else -> ""
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
