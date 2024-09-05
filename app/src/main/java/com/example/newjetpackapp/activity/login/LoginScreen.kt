package com.example.newjetpackapp.activity.login

import android.annotation.SuppressLint
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
import com.example.newjetpackapp.R
import com.example.newjetpackapp.component.AppLogo
import com.example.newjetpackapp.networks.Resource
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.utils.CallFun
import com.example.newjetpackapp.utils.Dimensions


@SuppressLint("SuspiciousIndentation")
@Composable
/*fun LoginScreen(navController: NavController, arguments: Bundle){
    val username = arguments.getString(Const.USER_NAME) ?: Const.EMPTY
    val email = arguments.getString(Const.EMAIL) ?: Const.EMPTY
    Log.e("TAG", "LoginScreen: "+email)*/
fun LoginScreen(onNavigateSignUp: () -> Unit,onNavigateHome: () -> Unit, loginViewModel: LoginViewModel){
  /*  var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp)*/
    val context = LocalContext.current
    val (textMob, setTextState) = remember { mutableStateOf(TextFieldValue()) }
    val loginState by loginViewModel.loginState.observeAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(Dimensions.dp10)
        ) {
            item {
                AppLogo()
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
                ElevatedButton(modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = App_color,
                    contentColor = Color.White
                ),
                    onClick = {
                        if (textMob.text.isEmpty()) {
                            CallFun.showShort(context,"Please enter mobile number")
                        }else{
                            loginViewModel.loginApi(textMob.text)
                        }
                    }
                ) { Text(stringResource(R.string.login)) }
                Spacer(modifier = Modifier.height(Dimensions.dp20))
                Text(modifier = Modifier.clickable {
                    onNavigateSignUp()
                },text = stringResource(R.string.sign_up_here),
                    textAlign = TextAlign.Center, color = Color.Gray)

            }
        }
    }
    when (val result = loginState) {
        is Resource.Success -> {
            CallFun.showLog("TAg","Data: ${result.data.token}")
            val apiResponse = result.data
              if (apiResponse.status){
                  onNavigateHome()
              }else{
                  CallFun.showShort(context,"Login failed")
              }
        }
        is Resource.Failure -> {
            CallFun.showShort(context, result.exception.message ?: "Login failed")
        }
        else -> {
        }
    }
}