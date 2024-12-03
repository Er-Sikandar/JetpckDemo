package com.example.newjetpackapp.activity

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.component.appBar
import com.example.newjetpackapp.theme.Primary_color

@Composable
fun NotyScreen(onBackToHome: () -> Unit){
    val context = LocalContext.current

    Scaffold(
        topBar={
            appBar("Notifications",onBackToHome,false,Icons.Filled.Info, onActClick = {
                Toast.makeText(context, "Clicked: Act", Toast.LENGTH_SHORT).show()
            })
        }
    ) {innerPadding->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(12) { index ->
                    Box(modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(8.dp))) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Toast.makeText(context, "Clicked: ${index + 1}", Toast.LENGTH_SHORT).show()
                            }
                            .padding(8.dp),) {
                            Icon(
                                Icons.Filled.Notifications,
                                modifier = Modifier.size(35.dp),
                                tint = Primary_color,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "To integrate the Scaffold structure you've mentioned within your NavHost setup and replace the existing content layout, you need to structure your MainScreen or whatever your root composable is as a Scaffold. This will provide the necessary top bar, bottom bar, and main content layout for your app. ${index + 1}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }


}