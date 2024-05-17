package com.example.newjetpackapp.component

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newjetpackapp.MainActivity
import com.example.newjetpackapp.SplashGo
import com.example.newjetpackapp.activity.HomeScreen
import com.example.newjetpackapp.activity.LoginScreen
import com.example.newjetpackapp.utils.Const


/**
 * Without Splash
 *    val navController = rememberNavController()
 *  val arguments = bundleOf(Const.USER_NAME to "Developer", Const.EMAIL to "sikandar@example.com")
 *     MyCusNav.MyNav(navController, Const.LOGIN_SCREEN,arguments) or
 *     MyCusNav.MyNav(navController, Const.LOGIN_SCREEN,Bundle.EMPTY)
 *
 *     navController.navigate(Const.MAIN_SCREEN)
 *
 *     With Splash
 *
 *  MyCusNav.MyNav(navController, Const.SPLASH_GO, Bundle.EMPTY)
 *
 *  LaunchedEffect(key1 = true) {
 *         delay(Const.SPLASH_TIME.toLong())
 *         navController.navigate(Const.LOGIN_SCREEN){
 *             launchSingleTop = true
 *             popUpTo(navController.graph.startDestinationId) {
 *                 inclusive = true  //back finish
 *             }
 *         }
 *     }
 *
 *
 *
 */
object MyCusNav{
    @Composable
     fun MyNav(navController: NavHostController,sDestination: String, arguments: Bundle) {
        NavHost(navController, startDestination = sDestination) {
            composable(Const.SPLASH_GO) {
                SplashGo(navController = navController)
            }
            composable(Const.LOGIN_SCREEN) {
                LoginScreen(navController=navController,arguments=arguments)
            }
            composable(Const.HOME_SCREEN) {
                HomeScreen(navController=navController)
            }
        }
    }

}