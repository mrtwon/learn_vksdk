package com.example.firstvk.Friends

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.firstvk.Interfaces.CallbackActivity
import com.example.firstvk.Interfaces.InterfaceCallbackFragmentFriends
import com.example.firstvk.LiveDataVK
import com.example.firstvk.R
import com.example.firstvk.VKUser

class FragmentFriendsOnline : Fragment(), InterfaceCallbackFragmentFriends, LifecycleObserver{
    init {
        Log.i("self-debug","FragmentFriendsOnline init")
    }
    var METKA: Boolean? = null
    var rv: RecyclerView? = null
    var vm: ViewModelOnlineFriends? = null
    var adapter: Adapter? = null
    val observer = object : Observer<ArrayList<VKUser>>{
        override fun onChanged(t: ArrayList<VKUser>?) {
            Log.i("self-f2-observer","observer start [${t?.size}]")
            adapter!!.notifyDataSetChanged()
            updateTitle()
        }

    }
    override fun onAttach(context: Context) {
        Log.i("self-f2","onAttach()")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("self-f2","onCreate()")
        vm = ViewModelProvider(this).get(ViewModelOnlineFriends::class.java)
        adapter = Adapter(vm!!.liveData.currentList)
        vm!!.liveData.observe(this, observer)
        lifecycle.addObserver(this)
        //retainInstance = true
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("self-f2","onCreateView()")
        val v =  inflater.inflate(R.layout.recyclerview_friends, container, false)
        rv = v.findViewById(R.id.recyclerview)
        rv!!.adapter = adapter
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("self-f2","onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.i("self-f2","onResume()")
        vm!!.getFriendsOnline()
        super.onResume()
    }

    override fun onPause() {
        Log.i("self-f2","onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.i("self-f2","onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("self-f2","onDestroy()")
        vm = null
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("self-f2","onDetach()")
        super.onDetach()
    }
    override fun onDestroyView() {
        Log.i("self-f2","onDestroyView()")
        //rv = null
        super.onDestroyView()
    }

    override fun onUpdate() {
        METKA = true
        val isAdapter = adapter == null
        val isRecyclerView = rv == null
        Log.i("self-f2","onUpdate() | adapter ${isAdapter.toString()} | rv ${isRecyclerView.toString()}")
        vm!!.getFriendsOnline()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun allState(lc: LifecycleOwner, state: Lifecycle.Event){
        Log.i("self-f2", state.toString())
    }
    fun updateTitle(){
        val titleString = "Online(${vm!!.liveData.value!!.size})"
        (activity as CallbackActivity).editTitle(titleString)
    }
}