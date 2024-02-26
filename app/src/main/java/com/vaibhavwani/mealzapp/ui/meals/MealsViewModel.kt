package com.vaibhavwani.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.vaibhavwani.data.MealzRepository
import com.vaibhavwani.data_api.models.MealzCategoriesResponse

class MealsViewModel(
    private val repository: MealzRepository = MealzRepository()
): ViewModel() {

    fun getMealCategories(
        successCallback: (response: MealzCategoriesResponse) -> Unit
    ) = repository.getMeals(successCallback)
}