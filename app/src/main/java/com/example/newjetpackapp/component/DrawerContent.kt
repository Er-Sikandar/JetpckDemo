package com.example.newjetpackapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.R
import com.example.newjetpackapp.theme.App_color

@Composable
fun DrawerContent(onItemClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_camera),
                    contentDescription = "Logo",
                    colorFilter = ColorFilter.tint(App_color),
                    modifier = Modifier.size(120.dp)
                )
                Text("sikandar@gmail.com", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(5.dp))
                Text("+918601854014", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(10.dp))
            }

            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            LazyColumn(
                modifier = Modifier.padding(vertical = 0.dp, horizontal = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item { Spacer(modifier = Modifier.height(10.dp)) }

                items(15) { index ->
                    DrawerItem(
                        title = "Item ${index + 1}",
                        icon = Icons.Filled.KeyboardArrowUp,
                        onClick = { onItemClick("Drawer Item ${index + 1}") }
                    )
                }
            }
        }
        // Fixed content at the bottom
        Column(
            modifier = Modifier
                .align(BottomCenter)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "New JetPack App",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
            Text(
                text = "Version 1.0.0",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 0.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = 12.dp, horizontal = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Icon(icon, contentDescription = null, modifier = Modifier.rotate(90f))
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp),
            thickness = 1.dp, color = Color.Gray)
    }
}
