package com.alwan.made1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.alwan.made1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupNavigation() {
        val navController =
            binding.fragmentContainerMain.getFragment<NavHostFragment>().navController
        val navView = binding.navViewMain

        binding.bottomNavMain.setItemSelected(navController.graph.startDestinationId)
        binding.bottomNavMain.setOnItemSelectedListener { itemId ->
            navView.selectedItemId = itemId
        }

        NavigationUI.setupWithNavController(navView, navController)
    }
}