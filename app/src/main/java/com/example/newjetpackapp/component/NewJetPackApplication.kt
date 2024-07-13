package com.example.newjetpackapp.component

import android.app.Application
import android.content.Context
import android.util.Log

class NewJetPackApplication : Application() {
    private val TAG = "NewJetPackApplication"
    companion object {
        const val JETNEWS_APP_URI = "https://github.com/android/compose-samples"

        /*var appContext: Context? = null
        private var singleton: NewJetPackApplication? = null
            get() {
                if (singleton == null) {
                    singleton = NewJetPackApplication()
                }
                return singleton
            }*/

        private lateinit var appContext: Context
        private lateinit var singleton: NewJetPackApplication

        fun getAppContext(): Context {
            return appContext
        }
        @Synchronized
        fun getInstance(): NewJetPackApplication {
            return singleton
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        singleton = this
      //Todo Firebase init here
      //  Log.e(TAG, "onCreate: Pending Firebase Init..")
    }

    override fun onTerminate() {
        Log.e(TAG, "onTerminate: ")
        super.onTerminate()
    }

}