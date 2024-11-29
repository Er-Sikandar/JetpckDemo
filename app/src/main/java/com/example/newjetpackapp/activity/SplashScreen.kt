package com.example.newjetpackapp.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newjetpackapp.component.AppLogo
import com.example.newjetpackapp.utils.Const
import com.example.newjetpackapp.utils.Prefs
import kotlinx.coroutines.delay


@SuppressLint("SuspiciousIndentation")
@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit,
                   onNavigateToHome: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(modifier = Modifier.fillMaxSize()) {
                Column( modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SplashGo(onNavigateToLogin,onNavigateToHome)
                }
            }
    }
}
@Composable
fun SplashGo(onNavigateToLogin: () -> Unit,
             onNavigateToHome: () -> Unit) {
    AppLogo()
    LaunchedEffect(key1 = true) {
        delay(Const.SPLASH_TIME.toLong())
        val isUserLoggedIn = Prefs.getInstance().getPrefsBoolean(Const.IS_LOGIN)
        if (isUserLoggedIn) {
            onNavigateToHome()
            Log.e("TAG", "SplashGo:>> Home")
        }else{
            onNavigateToLogin()
            Log.e("TAG", "SplashGo Else:>> ")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppLogo()
            }
        }
        }
}
