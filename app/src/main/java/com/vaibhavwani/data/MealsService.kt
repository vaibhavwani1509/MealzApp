package com.vaibhavwani.data

import com.vaibhavwani.data_api.MealsApi
import com.vaibhavwani.data_api.models.MealzCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsService {

    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(
        successCallback: (response: MealzCategoriesResponse) -> Unit
    ) = api.getMeals().enqueue(object : Callback<MealzCategoriesResponse> {
        override fun onResponse(
            call: Call<MealzCategoriesResponse>,
            response: Response<MealzCategoriesResponse>
        ) {
            if (response.isSuccessful) {
                successCallback(response.body()!!)
            }
        }

        override fun onFailure(call: Call<MealzCategoriesResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}