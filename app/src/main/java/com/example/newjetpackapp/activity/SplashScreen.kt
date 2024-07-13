package com.example.newjetpackapp.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newjetpackapp.MyImage
import com.example.newjetpackapp.R
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.theme.NewJetPackAppTheme
import com.example.newjetpackapp.utils.Const
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
                    SplashGo()
                }
            }
    }
}
@Composable
fun SplashGo() {
    getImage()
    LaunchedEffect(key1 = true) {
        delay(Const.SPLASH_TIME.toLong())
        Log.e("TAG", "SplashGo:>> ")
    }
}
@Composable
fun getImage() {
    Image(
        painter = painterResource(id = R.drawable.baseline_camera),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(App_color),
        modifier = Modifier.size(180.dp)
    )
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
                getImage()
            }
        }
        }
}
