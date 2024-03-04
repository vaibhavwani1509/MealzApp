package com.vaibhavwani.mealzapp.ui.meals.details

import androidx.lifecycle.ViewModel
import com.vaibhavwani.data.MealzRepository

class MealDetailsViewModel(): ViewModel() {

    val repository = MealzRepository.getInstance()

    fun getMealDetails(
        id: String
    ) = repository.getMealDetails(id)
}