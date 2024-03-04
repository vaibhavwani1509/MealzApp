package com.vaibhavwani.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.vaibhavwani.data_api.models.Category

@Composable
fun MealsCategoriesScreen(
    getDetails: (String) -> Unit,
) {
    val viewModel: MealsViewModel = viewModel()
    if (viewModel.categories.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.wrapContentSize().align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.categories.value) {
                it?.let {
                    CategoryCard(it, getDetails)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    getDetails: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExpandableCard(
        id = category.id,
        image = category.image,
        title = category.name,
        description = category.description,
        isExpanded = isExpanded,
        onExpandToggle = { isExpanded = !isExpanded },
        getDetails = getDetails,
    )
}

@Composable
fun ExpandableCard(
    id: String? = "",
    image: String? = "",
    title: String? = "",
    description: String? = "",
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    getDetails: (String) -> Unit,
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
            val (imageRef, titleRef, iconRef, moreInfoRef) = createRefs()

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
                            bottom.linkTo(titleRef.top)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                            centerHorizontallyTo(parent)
                        } else {
                            start.linkTo(parent.start)
                            end.linkTo(titleRef.start)
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
                            bottom.linkTo(moreInfoRef.top)
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
                    maxLines = if (isExpanded) 5 else 2,
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

            if (isExpanded) {
                Button(
                    modifier = Modifier
                        .animateContentSize()
                        .wrapContentSize()
                        .constrainAs(moreInfoRef) {
                            top.linkTo(titleRef.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    onClick = {
                        getDetails(id ?: "")
                    }
                ) {
                    Text(
                        text = "More details",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
