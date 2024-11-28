package com.example.newjetpackapp.activity

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.BottomNavigationBar
import com.example.newjetpackapp.utils.Prefs
import kotlinx.coroutines.delay
@Composable
fun HomeScreen(onNavHomeToProfile:()->Unit, onNavHomeToSettings:()->Unit, onNavigateHomeToLogin:()->Unit, onExitApp: () -> Unit){
    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(onClick ={onNavHomeToProfile()}) {
                Text("Go to Profile")
            }
            ElevatedButton(onClick ={onNavHomeToSettings()} ) {
                Text("Go to Settings")
            }

            ElevatedButton(onClick = {
                    Prefs.getInstance().logout()
                    onNavigateHomeToLogin()
                }) {
                Text(stringResource(R.string.clear_data))
            }



        }



    }
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