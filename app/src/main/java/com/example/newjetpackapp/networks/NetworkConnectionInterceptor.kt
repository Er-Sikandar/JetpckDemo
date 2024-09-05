package com.example.newjetpackapp.networks

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(val mContext: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw Exception("Please connect with internet")
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}