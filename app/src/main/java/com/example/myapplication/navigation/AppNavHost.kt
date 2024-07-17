package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.ProductDetailsScreen
import com.example.myapplication.screens.ProductListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.PRODUCT_LIST
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.PRODUCT_LIST) {
            ProductListScreen(navController)
        }
//        composable(NavigationItem.PRODUCT_DETAILS) {
//            ProductDetailsScreen(navController)
//        }
        composable(route= "${NavigationItem.PRODUCT_DETAILS}/{id}", arguments = listOf(navArgument("id"){
            type = NavType.StringType
        })) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if(id!=null)
            ProductDetailsScreen(navController, id)
        }

    }
}