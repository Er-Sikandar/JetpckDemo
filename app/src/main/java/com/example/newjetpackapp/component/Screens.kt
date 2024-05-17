package com.example.newjetpackapp.component

import android.os.Bundle
import com.example.newjetpackapp.utils.Const

sealed class Screens(val route:String){
   data object SplashScreen : Screens(route = Const.SPLASH_GO)
     data object LoginScreen : Screens(route = Const.LOGIN_SCREEN)

   fun withArgs(vararg args:String) : String {
       return buildString {
           append(route)
           args.forEach { arg ->
               append("/$arg")
           }
       }
   }

}
