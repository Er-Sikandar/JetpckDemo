package com.example.newjetpackapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.newjetpackapp.component.NewJetPackApplication

class Prefs {

    companion object {
        private const val PREF_NAME = "NewJetPack_App"
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
        fun getInstance(): Prefs {
            val masterKey: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            sharedPreferences = EncryptedSharedPreferences.create(PREF_NAME, masterKey,
                NewJetPackApplication.getAppContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
            editor = sharedPreferences.edit()
            return Prefs()
        }
    }

    fun setPrefsString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getPrefsString(key: String): String {
        return sharedPreferences.getString(key, Const.EMPTY).toString()
    }
    fun setPrefsInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getPrefsInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
    fun getPrefsBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setPrefsBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)?.apply()
    }




    fun remove(key: String?) {
        editor.remove(key)?.apply()
    }
    fun logout() {
        editor.clear().apply()
    }

}