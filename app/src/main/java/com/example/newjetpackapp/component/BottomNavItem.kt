package com.example.newjetpackapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newjetpackapp.component.Destinations.HOME_TO_HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.PROFILE_ROUTE
import com.example.newjetpackapp.component.Destinations.SETTINGS_ROUTE

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object HomeToHome : BottomNavItem(HOME_TO_HOME_ROUTE, "Home", Icons.Default.Home)
    object Profile : BottomNavItem(PROFILE_ROUTE, "Profile", Icons.Default.Person)
    object Settings : BottomNavItem(SETTINGS_ROUTE, "Settings", Icons.Default.Settings)
}