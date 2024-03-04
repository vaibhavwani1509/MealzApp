package com.vaibhavwani.mealzapp.ui.meals.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vaibhavwani.data_api.models.Category

@Composable
fun CategoryDetailsScreen(
    detailsViewModel: MealDetailsViewModel,
    id: String
) {
    val meal = remember {
        mutableStateOf<Category?>(null)
    }
    LaunchedEffect(key1 = "details") {
        meal.value = detailsViewModel.getMealDetails(id)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        meal.value?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                model = it.image ?: "",
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                text = it.name ?: "",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = it.description ?: "",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
            )
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Error while fetching details",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}