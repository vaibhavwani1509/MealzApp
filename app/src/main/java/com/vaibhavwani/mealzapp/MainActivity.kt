package com.vaibhavwani.mealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vaibhavwani.mealzapp.ui.meals.MealsCategoriesScreen
import com.vaibhavwani.mealzapp.ui.meals.MealsViewModel
import com.vaibhavwani.mealzapp.ui.meals.details.CategoryDetailsScreen
import com.vaibhavwani.mealzapp.ui.meals.details.MealDetailsViewModel
import com.vaibhavwani.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MealsViewModel>()
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealzNavigation()
                }
            }
        }
    }
}

@Composable
fun MealzNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories_list") {
        composable(route = "categories_list") {
            MealsCategoriesScreen() { id ->
                navController.navigate("category_detail/$id")
            }
        }

        composable(
            route = "category_detail/{category_id}",
            arguments = listOf(
                navArgument(
                    name = "category_id"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            val detailsViewModel: MealDetailsViewModel = viewModel()
            CategoryDetailsScreen(
                detailsViewModel,
                it.arguments?.getString("category_id").orEmpty()
            )
        }
    }
}

