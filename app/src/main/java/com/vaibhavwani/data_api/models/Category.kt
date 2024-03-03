package com.vaibhavwani.data_api.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") val id: String? = "",
    @SerializedName("strCategory") val name: String? = "",
    @SerializedName("strCategoryDescription") val description: String? = "",
    @SerializedName("strCategoryThumb") val image: String? = ""
)