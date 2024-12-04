package com.momtaz.food.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.momtaz.food.pojo.Meal
import com.momtaz.food.pojo.MealList
import com.momtaz.food.retro.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(p0: Call<MealList>, p1: Response<MealList>) {
                if (p1.body() != null) {
                    val randomMeal: Meal? = p1.body()?.meals?.get(0)
                    randomMealLiveData.value = randomMeal!!

                } else {
                    return
                }
            }

            override fun onFailure(p0: Call<MealList>, p1: Throwable) {
                Log.d("Test",p1.message.toString())
            }

        })
    }
    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
}