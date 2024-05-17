package com.example.newjetpackapp.component

import android.app.Application
import android.content.Context
import android.util.Log

class NewJetPackApplication : Application() {
    private val TAG = "NewJetPackApplication"
    companion object {
        const val JETNEWS_APP_URI = "https://github.com/android/compose-samples"
    }
    private lateinit var context: Context
    private lateinit var mInstance: NewJetPackApplication

    fun getAppContext(): Context {
        return context
    }

    @Synchronized
    fun getInstance(): NewJetPackApplication {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        mInstance = this
      //Todo Firebase init here
      //  Log.e(TAG, "onCreate: Pending Firebase Init..")
    }

    override fun onTerminate() {
        Log.e(TAG, "onTerminate: ")
        super.onTerminate()
    }

}