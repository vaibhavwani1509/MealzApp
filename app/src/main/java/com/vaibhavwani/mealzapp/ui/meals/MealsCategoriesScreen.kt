package com.vaibhavwani.mealzapp.ui.meals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vaibhavwani.data_api.models.Category

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsViewModel = viewModel()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(viewModel.categories.value) {
            it?.let {
                CategoryCard(it)
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category
) {
    Card(
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.padding(16.dp),
                model = category.strCategoryThumb,
                contentDescription = null
            )
            Text(text = category.strCategory ?: "")
        }
    }
}