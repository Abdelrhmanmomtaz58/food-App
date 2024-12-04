package com.momtaz.food.retro

import com.momtaz.food.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealAPI {
    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}