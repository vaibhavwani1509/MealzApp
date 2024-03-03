package com.vaibhavwani.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import com.vaibhavwani.data.MealzRepository
import com.vaibhavwani.data_api.models.MealzCategoriesResponse

class MealsViewModel(
    private val repository: MealzRepository = MealzRepository()
): ViewModel() {

    suspend fun getMealCategories() = repository.getMeals().categories
}