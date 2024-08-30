package com.example.newjetpackapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newjetpackapp.activity.LoginScreen
import com.example.newjetpackapp.component.MyCusNav
import com.example.newjetpackapp.component.NewGetNavHost
import com.example.newjetpackapp.component.Screens
import com.example.newjetpackapp.theme.App_color
import com.example.newjetpackapp.theme.NewJetPackAppTheme
import com.example.newjetpackapp.utils.Const
import kotlinx.coroutines.delay

/**
 *  Sample Google Code
 * https://github.com/android/compose-samples
 */

class MainActivity : ComponentActivity() {
    private val TAG="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewJetPackAppTheme {
                NewGetNavHost()
            }
        }

    }
}










@Composable
fun SplashGo(navController: NavController) {
    Log.e("TAG", "SplashGo: ")
    MyImage()
    LaunchedEffect(key1 = true) {
        delay(Const.SPLASH_TIME.toLong())
        if (true){
            navController.navigate(Const.HOME_SCREEN) {
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true  //back finish
                }
            }
        }else{
            navController.navigate(Const.LOGIN_SCREEN) {
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true  //back finish
                }
            }
        }

    }
}

@Composable
fun NavGraph(navController: NavHostController,route:String) {
    NavHost(navController, startDestination = route) {
        composable(route = Screens.SplashScreen.route) {
            SplashGo(navController)
        }
        composable(route = Screens.LoginScreen.route) {
            val arguments = bundleOf(Const.USER_NAME to "Developer", Const.EMAIL to "sikandar@example.com")
           // LoginScreen(navController, arguments)
        }
    }
}

@Composable
fun MyGreeting(name: String) {
    Text(
        text = "Hello, $name !",
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        lineHeight = TextUnit.Unspecified,

        )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.baseline_camera),
        contentDescription = "Logo",
        colorFilter = ColorFilter.tint(App_color),
        modifier = Modifier.size(180.dp)
    )
}

@Composable
fun MyFun(navController: NavController) {
    Button(onClick = {
        // Navigate back to MainScreen
    }) {
        Text("Go back to Main Screen")
    }
}

@Composable
fun MyInFin() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
        },
        shape = RoundedCornerShape(8.dp),
        /*border = BorderStroke(0.5.dp, App_color),*/
    ) {
        Text(text = stringResource(R.string.next))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GreetingPreview() {
    NewJetPackAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyImage()
            }

        }
    }
    /* Column( modifier = Modifier.fillMaxSize(),
     verticalArrangement =Arrangement.Center,
     horizontalAlignment = Alignment.CenterHorizontally) {
         MyImage()
         *//*Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()
            Greeting(name = "NewJetPackApp")
            MyButton()*//*
    }*/


}

