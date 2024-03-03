package com.vaibhavwani.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
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
    val isExpanded = remember { mutableStateOf(false) }
    val maxLines by animateIntAsState(if (isExpanded.value) 10 else 4, label = "")
    Card(
        modifier = Modifier
            .animateContentSize()
            .padding(
            vertical = 8.dp,
            horizontal = 16.dp,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.padding(16.dp),
                model = category.image,
                contentDescription = null
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.name ?: "",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = category.description ?: "",
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Icon(
                modifier = Modifier
                    .align(Alignment.Top)
                    .clickable { isExpanded.value = !isExpanded.value }
                    .padding(16.dp),
                imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}