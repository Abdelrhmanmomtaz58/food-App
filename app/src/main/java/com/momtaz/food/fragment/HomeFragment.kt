package com.momtaz.food.fragment

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.momtaz.food.activity.MealActivity
import com.momtaz.food.databinding.FragmentHomeBinding
import com.momtaz.food.pojo.Meal
import com.momtaz.food.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvM: HomeViewModel
    private lateinit var randomMeal:Meal

    companion object{
        const val MEAL_ID = "com.momtaz.food.fragment.idMeal"
        const val MEAL_NAME = "com.momtaz.food.fragment.nameMeal"
        const val MEAL_THUMB = "com.momtaz.food.fragment.thumbMeal"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvM = ViewModelProviders.of(this)[HomeViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvM.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()
    }

    private fun onRandomMealClick() {
        binding.cardRandomMeal.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeMvvM.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(this@HomeFragment)
                .load(value!!.strMealThumb)
                .into(binding.imgRandomMeal)
            this.randomMeal = value
        }
    }

}