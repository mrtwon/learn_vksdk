package com.example.firstvk.Friends

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firstvk.Interfaces.InterfaceLiveData
import com.example.firstvk.LiveDataVK
import com.example.firstvk.VKUser
import com.example.firstvk.requests.VKFriendsRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback

class ModelTest(val liveData: InterfaceLiveData<ArrayList<VKUser>>) {

    private fun executeFriends(vkApiCallback: VKApiCallback<List<VKUser>>) {
        VK.execute(VKFriendsRequest(), vkApiCallback)
    }

    fun writeFriendsList() {
        executeFriends(methodGetFriends)
    }

    fun updateFriendsList(){
        executeFriends(methodUpdateFriends)
    }
    fun writeFriendsListOnline(){
        executeFriends(methodGetFriendsOnline)
    }

    val methodGetFriends = object : VKApiCallback<List<VKUser>> {
        override fun fail(error: Exception) {
            Log.i("self-mt","error [  $error  ]")
            error.printStackTrace()
        }

        override fun success(result: List<VKUser>) {
            liveData.setValueLiveData(result as ArrayList<VKUser>)
        }
    }
    val methodUpdateFriends = object : VKApiCallback<List<VKUser>>{
        override fun fail(error: Exception) { error.printStackTrace() }

        override fun success(result: List<VKUser>) {
            liveData.setValueLiveData(result as ArrayList<VKUser>)
        }
    }
    val methodGetFriendsOnline = object : VKApiCallback<List<VKUser>> {
        override fun fail(error: Exception) {
            Log.i("self-mt","error [  $error  ]")
            error.printStackTrace()
        }

        override fun success(result: List<VKUser>) {
            Log.i("self-mt","success [size ${result.size}]")

            val newResult = result as ArrayList
            newResult.removeAll{ !it.isOnline }
            liveData.setValueLiveData(newResult)
        }
    }
}