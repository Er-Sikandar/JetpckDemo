package com.example.newjetpackapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.R
import com.example.newjetpackapp.theme.App_color

@Composable
fun AppLogo() {
    Image(
        painter = painterResource(id = R.drawable.baseline_camera),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(App_color),
        modifier = Modifier.size(180.dp)
    )
}