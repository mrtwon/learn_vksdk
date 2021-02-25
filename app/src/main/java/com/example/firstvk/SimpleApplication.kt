package com.example.firstvk

import android.app.Application
import android.content.Intent
import android.util.Log
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class SimpleApplication : Application() {
    override fun onCreate() {
        Log.i("self-debug","onCreate in application")
        VK.addTokenExpiredHandler(tokenExpire)
        super.onCreate()
    }
    private val tokenExpire = object : VKTokenExpiredHandler{
        override fun onTokenExpired() {
            /*Log.i("self-debug","start method in application")
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)*/
        }

    }
}

