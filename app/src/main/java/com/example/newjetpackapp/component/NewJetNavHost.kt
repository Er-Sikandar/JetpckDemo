package com.example.newjetpackapp.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newjetpackapp.activity.HomeScreen
import com.example.newjetpackapp.activity.ProfileScreen
import com.example.newjetpackapp.activity.SettingsScreen
import com.example.newjetpackapp.activity.login.LoginScreen
import com.example.newjetpackapp.activity.SignUp
import com.example.newjetpackapp.activity.SplashScreen
import com.example.newjetpackapp.activity.login.LoginViewModel
import com.example.newjetpackapp.component.Destinations.HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.LOGIN_ROUTE
import com.example.newjetpackapp.component.Destinations.PROFILE_ROUTE
import com.example.newjetpackapp.component.Destinations.SETTINGS_ROUTE
import com.example.newjetpackapp.component.Destinations.SIGNUP_ROUTE
import com.example.newjetpackapp.component.Destinations.SPLASH_ROUTE
import com.example.newjetpackapp.utils.Const
import com.example.newjetpackapp.utils.Prefs

object Destinations {
    const val SPLASH_ROUTE = "splash"
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"
    const val SIGNUP_ROUTE = "signup"
    const val PROFILE_ROUTE = "profile"
    const val SETTINGS_ROUTE = "settings"

}

@Composable
fun NewGetNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        composable(SPLASH_ROUTE) {
            SplashScreen(
              onNavigateToLogin = {
                  navController.navigate(LOGIN_ROUTE){
                      popUpTo(navController.graph.startDestinationId) {
                          inclusive = true
                      }
                  }
              },
              onNavigateToHome = {
                  navController.navigate(HOME_ROUTE){
                      popUpTo(navController.graph.startDestinationId) {
                          inclusive = true
                      }
                  }
              },
          )
        }

        composable(LOGIN_ROUTE) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                onNavigateSignUp = {
                    navController.navigate(SIGNUP_ROUTE)
                },
                onNavigateHome = {
                    navController.navigate(HOME_ROUTE){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel
            )
        }
        composable(SIGNUP_ROUTE) {
            val activity = (LocalContext.current as? Activity)
            SignUp(
                onBack={
                    navController.popBackStack()
                    /*
                    activity?.onBackPressed()
                    */
               }
            )
        }
        composable(HOME_ROUTE) {
            val activity = (LocalContext.current as? Activity)
            HomeScreen(
                navController,
                onNavHomeToProfile = {
                    navController.navigate(PROFILE_ROUTE)
                },
                onNavHomeToSettings = {
                    navController.navigate(SETTINGS_ROUTE)
                },

                onNavigateHomeToLogin = {
                    navController.navigate(LOGIN_ROUTE){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onExitApp = {
                    activity?.finish()
                }
            )
        }
        composable(PROFILE_ROUTE) {
            ProfileScreen(
                onBackToHome = {
                    navController.popBackStack()
                }
            )
        }

        composable(SETTINGS_ROUTE) {
            SettingsScreen(
                onBackToHome = {
                    navController.popBackStack()
                }
            )
        }




    }
}