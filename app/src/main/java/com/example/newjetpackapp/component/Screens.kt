package com.example.newjetpackapp.component

import com.example.newjetpackapp.utils.Const

sealed class Screens(val route:String){
    data object MainScreen : Screens(route = Const.MAIN_SCREEN)
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
