package com.example.newjetpackapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.Person)
    object Settings : BottomNavItem("settings", "Settings", Icons.Default.Settings)
}