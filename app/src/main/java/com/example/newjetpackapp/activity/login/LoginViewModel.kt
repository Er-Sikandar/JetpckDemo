package com.example.newjetpackapp.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newjetpackapp.networks.ApiClient
import com.example.newjetpackapp.networks.ApiResponse
import com.example.newjetpackapp.networks.Resource
import com.example.newjetpackapp.utils.CallFun
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class LoginViewModel: ViewModel() {
    private val _loginState = MutableLiveData<Resource<ApiResponse>>()
    val loginState: LiveData<Resource<ApiResponse>> get() = _loginState

     fun loginApi(mobileNumber: String) {
         _loginState.value = Resource.Loading
         val param: MutableMap<String, String> = HashMap()
         // param[Const.MOBILE_NO] = mobileNumber.trim()
          param["email"] = "eve.holt@reqres.in"
          param["password"] = "cityslicka"
        val api=ApiClient.getInstance().login(param)
        api.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    _loginState.value = Resource.Success(response.body()!!)
                } else {
                    _loginState.value = Resource.Failure(Exception("Login failed with code: ${response.code()}"))
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _loginState.value = Resource.Failure(t)
            }
        })
    }
    fun resetLoginState() {
        _loginState.value = Resource.Idle
    }

}