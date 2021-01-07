package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        //set up binding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        setSupportActionBar(binding.toolbar)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment

        //set up UpButton on action bar
//        val navController = this.findNavController(R.id.myNavHostFragment)
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }

    /* Code could be used for a future feature such as implementing an activity-wide menu item */

//    //Create menu item
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//         super.onCreateOptionsMenu(menu)
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.options_menu, menu)
//        return true
//    }
//
//    //handle menu click event
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return NavigationUI.onNavDestinationSelected(item,
//        this.findNavController(R.id.myNavHostFragment)) || super.onOptionsItemSelected(item)
//    }
}
