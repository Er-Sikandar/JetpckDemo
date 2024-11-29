package com.example.newjetpackapp.activity

import android.app.Activity
import android.content.Context
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.AppLogo
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.theme.appShapes
import com.example.newjetpackapp.utils.CallFun
import com.example.newjetpackapp.utils.Dimensions

@Composable
fun SignUp(onBack:()->Unit){
    val context = LocalContext.current
    var textName by remember { mutableStateOf("") }
    val (textMob, setTextState) = remember { mutableStateOf(TextFieldValue()) }
    var textEmail by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(Dimensions.dp10)
        ) { item {
            AppLogo(180.dp)
            Spacer(modifier = Modifier.height(Dimensions.dp20))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textName,
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "userIcon") },
                onValueChange = {
                    textName = it
                },
                singleLine = true,
                label = { Text("Enter Name Here..") },
                placeholder = { Text("Enter Name Here")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                )
            )
            Spacer(modifier = Modifier.height(Dimensions.dp10))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textMob,
                leadingIcon = { Icon(imageVector = Icons.Default.Call, contentDescription = "callIcon") },
                onValueChange = { newValue->
                    if (newValue.text.length <= 10) {
                        setTextState(newValue)
                    }
                },
                singleLine = true,
                label = { Text("Enter Mobile Number Here..") },
                placeholder = { Text("Enter Mobile Number Here")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.None
                )
            )
            Spacer(modifier = Modifier.height(Dimensions.dp10))
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textEmail,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
                onValueChange = {
                    textEmail = it
                },
                singleLine = true,
                label = { Text("Enter Email Id Here..") },
                placeholder = { Text("Enter Email Id Here")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.None
                )
            )
            Spacer(modifier = Modifier.height(Dimensions.dp60))
            ElevatedButton(modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = App_color,
                contentColor = Color.White
                  ),
                    onClick = {
                        if (textName.isEmpty()) {
                        CallFun.showShort(context,"Please enter name")
                        }else if (textMob.text.isEmpty()){
                            CallFun.showShort(context,"Please enter mobile number")
                        }else if (textEmail.isEmpty()){
                            CallFun.showShort(context,"Please enter email id")
                        }else{
                        onBack()
                        }
                    }
                ) { Text(stringResource(R.string.submit)) }
            }
        }
    }
}

/**
 * Separate Methods
 */
fun validateForm(
    context: Context,
    textName: String,
    textMob: String,
    textEmail: String,
    onSuccess: () -> Unit
) {
    when {
        textName.isEmpty() -> CallFun.showShort(context, "Please enter name")
        textMob.isEmpty() -> CallFun.showShort(context, "Please enter mobile number")
        textEmail.isEmpty() -> CallFun.showShort(context, "Please enter email id")
        else -> onSuccess()
    }
}