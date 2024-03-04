package com.vaibhavwani.mealzapp.ui.meals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
    var isExpanded by remember { mutableStateOf(false) }

    ExpandableCard(
        image = category.image,
        title = category.name,
        description = category.description,
        isExpanded = isExpanded
    ) {
        isExpanded = !isExpanded
    }
}

@Composable
fun ExpandableCard(
    image: String? = "",
    title: String? = "",
    description: String? = "",
    isExpanded: Boolean,
    onExpandToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            )
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (imageRef, titleRef, iconRef) = createRefs()

            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        if (isExpanded) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(titleRef.top)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                            centerHorizontallyTo(parent)
                        } else {
                            start.linkTo(parent.start)
                            end.linkTo(titleRef.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.wrapContent
                            height = Dimension.wrapContent
                        }
                    }
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(titleRef) {
                        if (isExpanded) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(imageRef.bottom)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                            centerHorizontallyTo(parent)
                        } else {
                            start.linkTo(imageRef.end)
                            end.linkTo(iconRef.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }
                    }
            ) {
                Text(
                    text = title ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                )

                Text(
                    text = description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) 10 else 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(iconRef) {
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
                    .clickable { onExpandToggle() }
            )
        }
    }
}
