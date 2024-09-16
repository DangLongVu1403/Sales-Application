package com.example.androidnangcao.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.ActivityMainLoggedUsersBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityLoggedUsers : AppCompatActivity() {
    private lateinit var binding: ActivityMainLoggedUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainLoggedUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_logged)
        navView.setupWithNavController(navController)
    }
}