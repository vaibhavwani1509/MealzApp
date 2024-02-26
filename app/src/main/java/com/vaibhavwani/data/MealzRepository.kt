package com.vaibhavwani.data

import com.vaibhavwani.data_api.models.MealzCategoriesResponse

class MealzRepository(
    private val mealsService: MealsService = MealsService()
) {

    fun getMeals(
        successCallback: (response: MealzCategoriesResponse) -> Unit
    ) = mealsService.getMeals(successCallback)
}