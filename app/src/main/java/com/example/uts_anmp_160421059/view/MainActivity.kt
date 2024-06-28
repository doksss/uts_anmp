package com.example.uts_anmp_160421059.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.ActivityMainBinding
import com.example.uts_anmp_160421059.model.Game
import com.example.uts_anmp_160421059.viewmodel.ListGameViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    internal lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListGameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as
                NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navController)
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.visibility = View.GONE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)


        viewModel = ViewModelProvider(this).get(ListGameViewModel::class.java)

//        val game = Game("Test", "deskripsi", "pengarang", "url")
//        viewModel.addGame(game)

//        //android back button
//        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment)as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }

//    //android back button
        override fun onSupportNavigateUp(): Boolean {
        //artinysa bisa memunculkan drawer atau memunculkan backbutton di kiri atas
//        return NavigationUI.navigateUp(navController,binding.drawerLayout) || super.onSupportNavigateUp()
        return NavigationUI.navigateUp(navController,binding.drawerLayout) || super.onSupportNavigateUp()
}
}