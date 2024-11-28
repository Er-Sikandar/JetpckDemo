package com.example.newjetpackapp.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.newjetpackapp.R
import com.example.newjetpackapp.utils.Prefs

@Composable
fun ProfileScreen(onBackToHome: () -> Unit){

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            ElevatedButton(
                onClick = {onBackToHome()},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Home")
            }
            Text(text = "Profile")
        }

    }

}