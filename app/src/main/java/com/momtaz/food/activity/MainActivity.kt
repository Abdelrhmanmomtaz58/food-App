package com.momtaz.food.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.momtaz.food.R
import com.momtaz.food.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Change the fragment in the activity according to the icon in the navigation button
        val navigationController = Navigation.findNavController(this, R.id.host_fragment)
        NavigationUI.setupWithNavController(binding.btmNav,navigationController)
        // end
    }
}