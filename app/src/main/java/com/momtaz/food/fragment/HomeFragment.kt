package com.momtaz.food.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.momtaz.food.R
import com.momtaz.food.databinding.FragmentHomeBinding
import com.momtaz.food.pojo.Meal
import com.momtaz.food.pojo.MealList
import com.momtaz.food.retro.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(p0: Call<MealList>, p1: Response<MealList>) {
                if (p1.body() != null) {
                    val randomMeal: Meal? = p1.body()?.meals?.get(0)
                    Glide.with(this@HomeFragment)
                        .load(randomMeal?.strMealThumb)
                        .into(binding.imgRandomMeal)
                } else {
                    return
                }
            }

            override fun onFailure(p0: Call<MealList>, p1: Throwable) {
                Log.d("Test",p1.message.toString())
            }

        })

    }

}