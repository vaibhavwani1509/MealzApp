package com.vaibhavwani.mealzapp.ui.meals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhavwani.data.MealzRepository
import com.vaibhavwani.data_api.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsViewModel(
    private val repository: MealzRepository = MealzRepository.getInstance()
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            categories.value = getMealCategories() ?: emptyList()
        }
    }

    val categories = mutableStateOf(emptyList<Category?>())

    suspend fun getMealCategories() = repository.getMeals().categories
}