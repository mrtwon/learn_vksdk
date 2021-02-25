package com.example.firstvk.Friends

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.firstvk.Interfaces.InterfaceCallbackFragmentFriends
import com.example.firstvk.LiveDataVK
import com.example.firstvk.R
import com.example.firstvk.VKUser

class FragmentFriends : Fragment(), InterfaceCallbackFragmentFriends, LifecycleObserver {
    init {
        Log.i("self-debug","FragmentFriends init")
    }
    lateinit var rv: RecyclerView
    lateinit var vm: ViewModelFriends
    lateinit var adapter: Adapter
    val observer = object : Observer<ArrayList<VKUser>>{
        override fun onChanged(t: ArrayList<VKUser>?) {
            Log.i("self-f2-observer","observer start [${t?.size}]")
            adapter.notifyDataSetChanged()
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        vm = ViewModelProvider(this).get(ViewModelFriends::class.java)
        adapter = Adapter(vm.liveData.currentList)
        vm.liveData.observe(this, observer)
        lifecycle.addObserver(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("self-f1","onCreateView()")
        val v =  inflater.inflate(R.layout.recyclerview_friends, container, false)
        rv = v.findViewById(R.id.recyclerview)
        rv.adapter = adapter
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.i("self-f1","onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.i("self-f1","onResume()")
        vm.getFriends()
        super.onResume()
    }

    override fun onPause() {
        Log.i("self-f1","onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.i("self-f1","onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("self-f1","onDestroy()")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i("self-f1","onDetach()")
        super.onDetach()
    }
    override fun onDestroyView() {
        Log.i("self-f1","onDestroyView()")
        //rv = null
        super.onDestroyView()
    }

    override fun onUpdate() {
        vm.getFriends()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun allState(lc: LifecycleOwner, state: Lifecycle.Event){
        Log.i("self-f1", state.toString())
    }
}