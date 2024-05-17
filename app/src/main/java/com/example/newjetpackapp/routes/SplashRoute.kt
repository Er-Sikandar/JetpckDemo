package com.example.newjetpackapp.routes

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import com.example.newjetpackapp.activity.SplashScreen

@Composable
fun SplashRoute(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    SplashScreen(
        onNavigateToLogin = {

        },
        onNavigateToHome = {

        }
    )
}