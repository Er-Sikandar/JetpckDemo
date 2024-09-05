package com.example.newjetpackapp.networks

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.Objects

interface ApiService {
  /*  @POST("api/login")
    suspend fun login(@FieldMap fieldMap: Map<String, String>): Response<ApiResponse>*/

    @POST("api/login")
     fun login(@Body fieldBody:Map<String, String>): Call<ApiResponse>


}