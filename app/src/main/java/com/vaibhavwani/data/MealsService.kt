package com.vaibhavwani.data

import com.vaibhavwani.data_api.MealsApi
import com.vaibhavwani.data_api.models.MealzCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsService {

    private val api: MealsApi by lazy {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals() = api.getMeals()
}