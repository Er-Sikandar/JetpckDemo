package com.example.newjetpackapp.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newjetpackapp.networks.ApiClient
import com.example.newjetpackapp.networks.ApiResponse
import com.example.newjetpackapp.utils.Const
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _loginState = MutableLiveData<Result<ApiResponse>>()
    val loginState: LiveData<Result<ApiResponse>> get() = _loginState

    fun loginApi(mobileNumber: String) {
        val param: MutableMap<String, String> = HashMap()
          param[Const.MOBILE_NO] = mobileNumber.trim()
        viewModelScope.launch {
            try {
                val response = ApiClient.getInstance().login(param)
                if (response.isSuccessful) {
                    _loginState.value = Result.success(response.body()!!)
                } else {
                    _loginState.value = Result.failure(Exception("Login failed"))
                }
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
            }
        }
    }

}