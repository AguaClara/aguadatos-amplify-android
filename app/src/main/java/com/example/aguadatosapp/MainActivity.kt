package com.example.aguadatosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aguadatosapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set up bottom nav bar
        val navView: BottomNavigationView = binding.navView

        //set up nav controller
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //link nav controller with nav bar
        navView.setupWithNavController(navController)
    }
}

//TODO: is there a way to make pages that are scrollable have a minimum height of the parent's height?
// So if you don't need to scroll, it will expand to fill the page...