package com.example.firstvk.Friends

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.*
import androidx.viewpager.widget.ViewPager
import com.example.firstvk.Interfaces.CallbackActivity
import com.example.firstvk.Interfaces.InterfaceCallbackFragmentFriends
import com.example.firstvk.R
import com.google.android.material.tabs.TabLayout

class MainFragmentFriends : Fragment(), LifecycleObserver, CallbackActivity, InterfaceCallbackFragmentFriends{
    init {
        Log.i("self-debug","MainFragmentFriends init")
    }
    var tl: TabLayout? = null
    var vp: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("self-f-main","onCreate()")
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("self-f-main","onCreateView()")
        val v = inflater.inflate(R.layout.fragment_friends, container, false)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        tl = v.findViewById(R.id.tl)
        vp = v.findViewById(R.id.vp)
        vp!!.adapter = pagerAdapter
        tl!!.setupWithViewPager(vp!!)
        vp!!.adapter!!.notifyDataSetChanged()
        return v
    }


    class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        private val title = arrayOf("Friends", "Online")
        private val arrayFragment = ArrayList<Fragment>()
        init {
            Log.i("self-f-main","init PageAdapter list")
            arrayFragment.add(FragmentFriends())
            arrayFragment.add(FragmentFriendsOnline())
        }
        override fun getItem(position: Int): Fragment {
            Log.i("self-f-main", "getItem element ${position}")
            return arrayFragment[position]
        }
        override fun getCount(): Int = arrayFragment.size
        override fun getPageTitle(position: Int): CharSequence? = title[position]
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun allState(lc: LifecycleOwner, state: Lifecycle.Event){
        Log.i("self-f-main", state.toString())
    }

    override fun editTitle(item: String) {
        tl!!.getTabAt(1)!!.text = item
    }

    override fun onDetach() {
        Log.i("self-f-main","onDetach()")
        super.onDetach()
    }
    override fun onUpdate() {
        val cFragment: Fragment = (vp!!.adapter as PagerAdapter).getItem(vp!!.currentItem)
        Log.i("self-f-main","current State ${cFragment.lifecycle.currentState.toString()}")
        Log.i("self-f-main","My State ${lifecycle.currentState.toString()}")
        (cFragment as InterfaceCallbackFragmentFriends).onUpdate()
    }

}