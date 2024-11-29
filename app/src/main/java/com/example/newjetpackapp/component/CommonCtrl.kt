package com.example.newjetpackapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.R
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.theme.Primary_color

@Composable
fun AppLogo(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.baseline_camera),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(App_color),
        modifier = Modifier.size(size)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appBar(title: String, onBackToHome: () -> Unit, isAct: Boolean, icon: ImageVector,onActClick:()->Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackToHome()
            }) {
                Icon(Icons.Filled.KeyboardArrowDown,tint = Primary_color, modifier = Modifier
                    .size(35.dp)
                    .rotate(90f),contentDescription = null)
            }
        },
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(title)
            }
        },
        actions = {
            if (isAct) {
                IconButton(onClick = {
                        onActClick()
                }) {
                    Icon(
                        imageVector = icon,
                        tint = Primary_color,
                        contentDescription = null
                    )
                }
            } else {
                Box(modifier = Modifier.background(Color.White).size(50.dp))
            }
        },
    )
}