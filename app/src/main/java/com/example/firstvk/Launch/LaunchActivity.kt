package com.example.firstvk.Launch

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstvk.Friends.MainFragmentFriends
import com.example.firstvk.Interfaces.CallbackActivity
import com.example.firstvk.Interfaces.InterfaceCallbackFragmentFriends
import com.example.firstvk.R
import com.example.firstvk.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class LaunchActivity : AppCompatActivity(), CallbackActivity {
    lateinit var toolBarFriends: Toolbar
    lateinit var toolBarSetting: Toolbar
    lateinit var navigation: BottomNavigationView
    var currentToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.launch_activity)
        super.onCreate(savedInstanceState)
        setting()
        firstFragment()
        selectBar()
    }

    fun selectBar(cFragment: Fragment){
        when{
            (cFragment is MainFragmentFriends) -> setMenu(toolBarFriends)
            (cFragment is SettingFragment) -> setMenu(toolBarSetting)
        }
    }

    fun selectBar(){
        val cFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
        when{
            (cFragment is MainFragmentFriends) -> setMenu(toolBarFriends)
            (cFragment is SettingFragment) -> setMenu(toolBarSetting)
        }
    }

    fun setMenu(tb: Toolbar){
        currentToolbar?.visibility = View.GONE
        currentToolbar = tb
        tb.visibility = View.VISIBLE
    }
    fun setting(){
        navigation = findViewById(R.id.navigation)
        toolBarFriends = findViewById(R.id.toolbar_1)
        toolBarSetting = findViewById(R.id.toolbar_2)

        menuInflater.inflate(R.menu.toolbar_friends, toolBarFriends.menu)
        menuInflater.inflate(R.menu.toolbar_setting, toolBarSetting.menu)


        toolBarSetting.title = getString(R.string.setting)
        toolBarFriends.title = getString(R.string.friends)

        toolBarFriends.setOnMenuItemClickListener(toolbarListener)
        toolBarSetting.setOnMenuItemClickListener(toolbarListener)
        navigation.setOnNavigationItemSelectedListener(navigateSelected)
        navigation.setOnNavigationItemReselectedListener(navigateReselected)

    }
    fun firstFragment(){

        val fragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
        when{
            fragment == null || fragment is SettingFragment -> startFragment(SettingFragment())
            fragment is MainFragmentFriends -> startFragment(MainFragmentFriends())
        }

    }
    private val navigateSelected = object: BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.navigation_setting -> {
                    val settingFragment = SettingFragment()
                    selectBar(settingFragment)
                    startFragment(settingFragment)
                }
                R.id.navigation_friends -> {
                    val fragmentFriendsTest = MainFragmentFriends()
                    selectBar(fragmentFriendsTest)
                    startFragment(fragmentFriendsTest)
                }
                else -> return false
            }
            return true
        }
    }
    private val navigateReselected = object: BottomNavigationView.OnNavigationItemReselectedListener{
        override fun onNavigationItemReselected(item: MenuItem) {
            Toast.makeText(this@LaunchActivity, "Blocked",Toast.LENGTH_SHORT).show()
        }

    }
    private fun startFragment(fragment: Fragment) {
        Log.i("self-launch","start fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }

    override fun editTitle(item: String) {
        val fragments = supportFragmentManager.findFragmentById(R.id.container_fragment)
        if(fragments != null){
            (fragments as CallbackActivity).editTitle(item)
        }
    }

    val toolbarListener = object: Toolbar.OnMenuItemClickListener{
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item!!.itemId){
                R.id.action_update ->{
                    Log.i("self-launch","click update")
                    val cFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
                    if(cFragment is InterfaceCallbackFragmentFriends){
                        (cFragment as InterfaceCallbackFragmentFriends).onUpdate()
                    }
                }
            }
            return true
        }

    }
}