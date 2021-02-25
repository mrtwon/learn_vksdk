package com.example.firstvk

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firstvk.Interfaces.InterfaceLiveData

class LiveDataVK: LiveData< ArrayList<VKUser>>(),
                            InterfaceLiveData<ArrayList<VKUser>> {
    lateinit var currentList: ArrayList<VKUser>
    init {
        currentList = ArrayList()
    }
    override fun setValueLiveData(list: ArrayList<VKUser>){
        this.value = list
        currentList.clear()
        currentList.addAll(this.value!!)
        Log.i("self-ld","size out list ${list.size} | size my list ${value?.size}")
    }

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}