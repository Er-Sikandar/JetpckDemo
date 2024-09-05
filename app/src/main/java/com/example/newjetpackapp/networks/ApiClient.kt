package com.example.newjetpackapp.networks

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    const val BASE_URL = "https://hvfchbdnv.com/eapis/"

    @SuppressLint("SuspiciousIndentation")
    fun getInstance():ApiService{
        val logging = HttpLoggingInterceptor()
        logging.level =HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
        httpClient.addInterceptor(logging)
    /*    httpClient.addInterceptor(NetworkConnectionInterceptor(context))*/

        val gson0 = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson0)).build()
        return retrofit.create(ApiService::class.java)
    }
}