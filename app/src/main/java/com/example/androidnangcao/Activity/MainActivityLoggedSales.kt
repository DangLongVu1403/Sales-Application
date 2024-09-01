package com.example.androidnangcao.Activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityMainLoggedSalesBinding

class MainActivityLoggedSales : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoggedSalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainLoggedSalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_logged)
        navView.setupWithNavController(navController)
    }
}