package com.vaibhavwani.data

import com.vaibhavwani.data_api.models.MealzCategoriesResponse

class MealzRepository(
    private val mealsService: MealsService = MealsService()
) {

    suspend fun getMeals() = mealsService.getMeals()
}