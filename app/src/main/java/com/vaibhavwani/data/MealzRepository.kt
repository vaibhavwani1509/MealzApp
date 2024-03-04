package com.vaibhavwani.data

import com.vaibhavwani.data_api.models.Category
import com.vaibhavwani.data_api.models.MealzCategoriesResponse

class MealzRepository(
    private val mealsService: MealsService
) {
    var cachedMeals: List<Category?> = emptyList()

    suspend fun getMeals(): MealzCategoriesResponse {
        val response = mealsService.getMeals()
        cachedMeals = response.categories.orEmpty()
        return response
    }

    fun getMealDetails(
        id: String
    ) = cachedMeals.firstOrNull {
        it?.id == id
    }

    companion object {
        @Volatile
        private var instance: MealzRepository? = null

        fun getInstance(): MealzRepository {
            return instance ?: synchronized(this) {
                instance = MealzRepository(MealsService())
                instance!!
            }
        }
    }
}