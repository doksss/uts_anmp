package com.example.uts_anmp_160421059.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    internal lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as
                NavHostFragment).navController
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.visibility = View.GONE
//        //android back button
//        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment)as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }

//    //android back button
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }
}