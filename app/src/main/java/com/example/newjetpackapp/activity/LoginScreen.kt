package com.example.newjetpackapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newjetpackapp.R
import com.example.newjetpackapp.SplashGo
import com.example.newjetpackapp.component.Screens
import com.example.newjetpackapp.utils.Const


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController, arguments: Bundle){
    val username = arguments.getString(Const.USER_NAME) ?: Const.EMPTY
    val email = arguments.getString(Const.EMAIL) ?: Const.EMPTY

    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp)

    Surface(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, $username", fontStyle = FontStyle.Normal, fontSize = 18.sp)
                Text(text = "This is test code.", fontSize = 14.sp)
            }
            ElevatedButton(
                onClick = {
                    expanded = !expanded
                      navController.navigate(Const.SPLASH_GO)

                }
            ) {
                Text(if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more))
            }

        }


    }

}