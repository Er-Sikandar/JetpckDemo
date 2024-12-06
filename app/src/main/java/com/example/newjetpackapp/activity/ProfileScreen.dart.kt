package com.example.newjetpackapp.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.R
import com.example.newjetpackapp.utils.Prefs

@Composable
fun ProfileScreen(){

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Profile")
            Spacer(modifier = Modifier.height(50.dp))
            ElevatedButton(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
            }
        }

    }

}