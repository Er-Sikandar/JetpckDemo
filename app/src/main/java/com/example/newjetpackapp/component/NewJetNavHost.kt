package com.example.newjetpackapp.component

import android.app.Activity
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newjetpackapp.activity.HomeScreen
import com.example.newjetpackapp.activity.HomeToHomeScreen
import com.example.newjetpackapp.activity.NotyScreen
import com.example.newjetpackapp.activity.ProfileScreen
import com.example.newjetpackapp.activity.SettingsScreen
import com.example.newjetpackapp.activity.login.LoginScreen
import com.example.newjetpackapp.activity.SignUp
import com.example.newjetpackapp.activity.SliceScreen
import com.example.newjetpackapp.activity.SplashScreen
import com.example.newjetpackapp.activity.WebViewScreen
import com.example.newjetpackapp.activity.login.LoginViewModel
import com.example.newjetpackapp.component.Destinations.HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.HOME_TO_HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.LOGIN_ROUTE
import com.example.newjetpackapp.component.Destinations.NOTIFICATIONS_ROUTE
import com.example.newjetpackapp.component.Destinations.PROFILE_ROUTE
import com.example.newjetpackapp.component.Destinations.SETTINGS_ROUTE
import com.example.newjetpackapp.component.Destinations.SIGNUP_ROUTE
import com.example.newjetpackapp.component.Destinations.SLICE_MENU_ROUTE
import com.example.newjetpackapp.component.Destinations.SPLASH_ROUTE
import com.example.newjetpackapp.component.Destinations.WEBVIEW_ROUTE
import com.example.newjetpackapp.utils.CallFun.showLog
import com.example.newjetpackapp.utils.Const
import com.example.newjetpackapp.utils.Prefs

object Destinations {
    const val SPLASH_ROUTE = "splash"
    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "signup"
    const val HOME_ROUTE = "home"
    const val HOME_TO_HOME_ROUTE = "home_to_home"
    const val PROFILE_ROUTE = "profile"
    const val SETTINGS_ROUTE = "settings"
    const val SLICE_MENU_ROUTE = "slice_menu"
    const val NOTIFICATIONS_ROUTE = "notifications"
    const val WEBVIEW_ROUTE = "webview?url={url}&type={type}"

}

@Composable
fun NewGetNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        composable(SPLASH_ROUTE) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(HOME_ROUTE) {
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
                    navController.navigate(HOME_ROUTE) {
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
                onBack = {
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
                onNavHomeToWeb = { url, type ->
                    navController.navigate("webview?url=${Uri.encode(url)}&type=$type")
                },
                onNavigateHomeToLogin = {
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                /*onNavHomeToHome = {
                    navController.navigate(HOME_TO_HOME_ROUTE)
                },
                onNavHomeToSettings = {
                    navController.navigate(SETTINGS_ROUTE)
                },*/
                onNavHomeToProfile = {
                    navController.navigate(PROFILE_ROUTE)
                },
                onNavHomeToNoty = {
                    navController.navigate(NOTIFICATIONS_ROUTE)
                },
                onNavHomeToSlice = {
                    navController.navigate(SLICE_MENU_ROUTE)
                },
                onExitApp = {
                    activity?.finish()
                }
            )
        }
        composable(HOME_TO_HOME_ROUTE) {
            HomeToHomeScreen(
                onBackToHome = {
                    navController.popBackStack()
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
        composable(NOTIFICATIONS_ROUTE) {
            NotyScreen(
                onBackToHome = {
                    navController.popBackStack()
                }
            )
        }
        composable(WEBVIEW_ROUTE, arguments = listOf(
            navArgument("url") { type = NavType.StringType },
            navArgument("type") { type = NavType.IntType }
        )) { backStackEntry ->

            val url = backStackEntry.arguments?.getString("url") ?: ""
            val type = backStackEntry.arguments?.getInt("type") ?: 0

            WebViewScreen(
                onBackToHome = { navController.popBackStack() },
                url = url,
                type = type
            )
        }

        composable(SLICE_MENU_ROUTE) {
            SliceScreen()
        }



    }
}