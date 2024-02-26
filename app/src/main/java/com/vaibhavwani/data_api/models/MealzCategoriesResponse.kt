package com.vaibhavwani.data_api.models

import com.google.gson.annotations.SerializedName

data class MealzCategoriesResponse(
    @SerializedName("categories") val categories: List<Category?>? = listOf()
)