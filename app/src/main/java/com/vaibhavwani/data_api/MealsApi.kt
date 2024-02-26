package com.vaibhavwani.data_api

import com.vaibhavwani.data_api.models.MealzCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    fun getMeals(): Call<MealzCategoriesResponse>
}