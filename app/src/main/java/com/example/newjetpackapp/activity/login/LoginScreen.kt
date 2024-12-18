package com.example.newjetpackapp.activity.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.example.newjetpackapp.MainActivity
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.AppLogo
import com.example.newjetpackapp.networks.Resource
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.utils.CallFun
import com.example.newjetpackapp.utils.Const
import com.example.newjetpackapp.utils.Dimensions
import com.example.newjetpackapp.utils.Prefs

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(onNavigateSignUp: () -> Unit,onNavigateHome: () -> Unit, loginViewModel: LoginViewModel){
    val context = LocalContext.current
    val (textMob, setTextState) = remember { mutableStateOf(TextFieldValue()) }
    val loginState by loginViewModel.loginState.observeAsState(Resource.Idle)
    var isLoading by remember { mutableStateOf(false) }

    when (val result = loginState) {
        is Resource.Loading -> {
            Text(text = "Loading...")
        }
        is Resource.Success -> {
            loginViewModel.resetLoginState()
            CallFun.showLog("TAg","Data: ${result.data.token}")
            val apiResponse = result.data
            if (!TextUtils.isEmpty(apiResponse.token)){
                Prefs.getInstance().setPrefsBoolean(Const.IS_LOGIN,true)
                onNavigateHome()
            }else{
                CallFun.showShort(context,"Login failed")
            }
        }
        is Resource.Failure -> {
            loginViewModel.resetLoginState()
            CallFun.showShort(context, result.exception.message ?: "Login failed")
        }
        is Resource.Idle -> {
            Log.e("TAG", "LoginScreen: Idle")
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(Dimensions.dp10)
        ) {
            item {
                AppLogo(180.dp)
                Spacer(modifier = Modifier.height(Dimensions.dp20))
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
                Spacer(modifier = Modifier.height(Dimensions.dp60))
                ElevatedButton(modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.dp45),
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(
                    containerColor = App_color,
                    contentColor = Color.White
                ),
                onClick = {
                        if (textMob.text.isEmpty()) {
                            CallFun.showShort(context, context.resources.getString(R.string.please_enter_mobile_number))
                        }else if (textMob.text.trim().length<10) {
                            CallFun.showShort(context, context.resources.getString(R.string.please_enter_valid_mobile_number))
                        }else{
                            isLoading = true
                           loginViewModel.loginApi(textMob.text)
                        }
                    }
                ) {
                    Text(stringResource(R.string.login))
                 }
                Spacer(modifier = Modifier.height(Dimensions.dp20))
                Text(modifier = Modifier.clickable {
                    onNavigateSignUp()
                },text = stringResource(R.string.sign_up_here),
                    textAlign = TextAlign.Center, color = Color.Gray)

            }
        }
    }

    BackHandler {
        if (!Prefs.getInstance().getPrefsBoolean(Const.IS_LOGIN)) {
            (context as? Activity)?.finish() // Exit the app
        }
    }
}