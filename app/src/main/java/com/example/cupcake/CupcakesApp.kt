package com.example.cupcake

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.CupecakesTheme

typealias ThemeToggle = () -> Unit

@Composable
fun CupcakesApp(onToggleTheme: () -> Unit, darkTheme: Boolean, context: Context) {
    CupecakesTheme(darkTheme = darkTheme) {
        val navController = rememberNavController()
        val viewModel = OrderViewModel()
        val onCancelOrder = {
            viewModel.resetOrder()
            navController.navigate(Routes.StartScreen.route)
        }
        NavHost(navController = navController, startDestination = Routes.StartScreen.route){
            composable(Routes.StartScreen.route){
                StartScreen(viewModel = viewModel, goToFlavorScreen = {navController.navigate(Routes.FlavorScreen.route)})
            }
            composable(Routes.FlavorScreen.route){
                FlavorScreen(sharedViewModel = viewModel, onCancelOrder = onCancelOrder, onClickNext = {navController.navigate(Routes.PickupScreen.route)})
            }
            composable(Routes.PickupScreen.route){
                PickupScreen(sharedViewModel = viewModel, onCancelOrder = onCancelOrder, onClickNext = {navController.navigate(Routes.SummaryScreen.route)})
            }
            composable(Routes.SummaryScreen.route){
                SummaryScreen(sharedViewModel = viewModel, context = context, onCancelOrder = onCancelOrder,)
            }
        }
    }


}

