package com.example.newjetpackapp.activity

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.BottomNavItem
import com.example.newjetpackapp.component.BottomNavigationBar
import com.example.newjetpackapp.component.Destinations.HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.HOME_TO_HOME_ROUTE
import com.example.newjetpackapp.component.Destinations.PROFILE_ROUTE
import com.example.newjetpackapp.component.Destinations.SETTINGS_ROUTE
import com.example.newjetpackapp.component.DrawerContent
import com.example.newjetpackapp.theme.Primary_color
import com.example.newjetpackapp.utils.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,onNavHomeToWeb:(String, Int)->Unit, onNavigateHomeToLogin:()->Unit,onNavHomeToProfile:()->Unit,onNavHomeToSlice:()->Unit,onNavHomeToNoty:()->Unit, onExitApp: () -> Unit){
    val context:Context = LocalContext.current
    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val drawerState:DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope:CoroutineScope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .background(MaterialTheme.colorScheme.surface)
            ) {
                DrawerContent(
                    onProClick={
                     onNavHomeToProfile()
                     coroutineScope.launch {
                      drawerState.close()
                     }
                },onItemClick = { item ->
                        coroutineScope.launch {
                            drawerState.close()
                        }
                       onNavHomeToWeb("https://jetpackcompose.net/", 0)
                      // onNavHomeToWeb("https://www.antennahouse.com/hubfs/xsl-fo-sample/pdf/basic-link-1.pdf", 1)
                        println("Clicked on Drawer: $item")
                })
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("New JetPack App")
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(Icons.Filled.Menu,tint = Primary_color,contentDescription = null)
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                onNavHomeToSlice()
                            }) {
                                Icon(Icons.Filled.Info,tint = Primary_color, contentDescription = null)
                            }
                            IconButton(onClick = {
                                onNavHomeToNoty()
                            }) {
                                Icon(Icons.Filled.Notifications,tint = Primary_color, contentDescription = null)
                            }
                        },
                    )
                },
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier.fillMaxSize().padding(innerPadding)
                ) {
                    /*NavHost(
                        navController = navController,
                        startDestination = "home_to_home",
                        modifier = Modifier.padding(innerPadding),
                        builder = {
                            composable("home_to_home") {
                               onNavHomeToHome()
                            }
                            composable("profile") {
                               onNavHomeToProfile()
                            }
                            composable("settings") {
                               onNavHomeToSettings()
                            }
                        })*/




                }

            }
        })
    /**
     * Double click back here..
     */
    BackHandler {
        if (doubleBackToExitPressedOnce) {
            onExitApp()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(doubleBackToExitPressedOnce) {
        if (doubleBackToExitPressedOnce) {
            delay(2000)
            doubleBackToExitPressedOnce = false
        }
    }

}

