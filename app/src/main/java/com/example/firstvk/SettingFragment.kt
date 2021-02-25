package com.example.firstvk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.vk.api.sdk.VK
import kotlin.math.log

class SettingFragment : Fragment(), View.OnClickListener {
    lateinit var logout: Button
    lateinit var login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)
        login = v.findViewById(R.id.login)
        logout = v.findViewById(R.id.logout)
        login.setOnClickListener(this)
        return v
    }

    override fun onStart() {
        logout.isEnabled = VK.isLoggedIn()
        super.onStart()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
                R.id.logout -> { VK.logout() }
        }
    }
}