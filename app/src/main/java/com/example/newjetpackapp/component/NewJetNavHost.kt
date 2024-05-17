package com.example.newjetpackapp.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newjetpackapp.component.Destinations.HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.LOGIN_ROUTE
import com.example.newjetpackapp.component.Destinations.SPLASH_ROUTE
import com.example.newjetpackapp.routes.SplashRoute

object Destinations {
    const val SPLASH_ROUTE = "splash"
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"

}

@Composable
fun NewGetNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        composable(SPLASH_ROUTE) {
          SplashRoute(
              onNavigateToLogin = {
                  navController.navigate(LOGIN_ROUTE)
              },
              onNavigateToHome = {
                  navController.navigate(HOME_ROUTE)
              },
          )
        }

    }
}