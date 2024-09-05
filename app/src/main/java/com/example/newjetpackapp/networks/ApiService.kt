package com.example.newjetpackapp.networks

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@FieldMap fieldMap: Map<String, String>): Response<ApiResponse>


}