package com.example.newjetpackapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object CallFun {
    fun showLog(tag: String, message: String) {
        Log.e(tag,"Values: $message")
    }
    fun showShort(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }



}