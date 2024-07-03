package com.example.cupcake

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.theme.CupecakesTheme

typealias ThemeToggle = () -> Unit

@Composable
fun CupcakesApp(onToggleTheme: () -> Unit, darkTheme: Boolean) {
    CupecakesTheme(darkTheme = darkTheme) {
        val navController = rememberNavController()
        val viewModel = OrderViewModel()
        NavHost(navController = navController, startDestination = Routes.StartScreen.route){
            composable(Routes.StartScreen.route){
                StartScreen(viewModel = viewModel, navHostController = navController,)
            }
            composable(Routes.FlavorScreen.route){
                FlavorScreen(viewModel = viewModel, navController = navController, )
            }

        }
    }


}