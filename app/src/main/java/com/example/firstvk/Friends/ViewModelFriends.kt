package com.example.firstvk.Friends

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firstvk.LiveDataVK

class ViewModelFriends : ViewModel() {
    var model: ModelTest
    var liveData: LiveDataVK

    init {
        liveData = LiveDataVK()
        model = ModelTest(liveData)
    }
    fun getFriends(){
        model.writeFriendsList()
        Log.i("self-vm","getFriends")
    }
    fun getFriendsOnline(){
        model.writeFriendsListOnline()
        Log.i("self-vm","getFriendsOnline")
    }
    fun updateFriends(){
        model.updateFriendsList()
        Log.i("self-vm", "updateFriends()")
    }
    override fun onCleared() {
        super.onCleared()
    }
}