package com.example.newjetpackapp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.MyCusNav
import com.example.newjetpackapp.component.Screens
import com.example.newjetpackapp.utils.Const

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(
                onClick = {

                   val args = bundleOf(Const.USER_NAME to "Developer", Const.EMAIL to "sikandar@example.com")

                   // navController.currentBackStackEntry?.arguments = args

                  //  navController.navigate(Const.LOGIN_SCREEN)




                }
            ) {
                Text(stringResource(R.string.home))
            }



        }



    }

}