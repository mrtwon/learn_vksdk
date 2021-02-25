/*
package com.example.firstvk.requests

import com.example.firstvk.VKUser
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

open class VKUsersRequest(uids : IntArray = intArrayOf()) : VKRequest<List<VKUser>>("users.get"){
    init {
        addParam("fields","online")
        addParam("fields", "photo")
    }

    override fun parse(r: JSONObject): List<VKUser> {
        val result = ArrayList<VKUser>()
    }
}*/
