package com.example.aguadatosapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aguadatosapp.databinding.ActivityMainBinding
import android.util.Log
import androidx.navigation.ui.NavigationUI

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set navigation view and controller
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_configuration
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //FIXME: navigation bar not working

        // Adding logging to detect item clicks
        navView.setOnItemSelectedListener { item ->
            Log.d("HomeActivity", "Navigation item selected: ${item.itemId}")
            val handled = NavigationUI.onNavDestinationSelected(item, navController)
            Log.d("HomeActivity", "Navigation handled: $handled")
            handled
        }
    }
}