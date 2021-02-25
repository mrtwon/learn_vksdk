package com.example.firstvk.Friends
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.firstvk.R
import com.example.firstvk.VKUser
import com.squareup.picasso.Picasso

open class Adapter(val userList : List<VKUser>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    init {
        Log.i("self-rv", "init recyclerview")
    }

    var count = 0
    var currentClick: View? = null

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {
        lateinit var list: VKUser
        lateinit var first_name: TextView
        lateinit var last_name: TextView
        lateinit var layout_container: LinearLayout
        lateinit var imageAvatar: ImageView
        lateinit var imageOnline: ImageView

        init {
            first_name = itemView!!.findViewById(R.id.tv_firstName)
            last_name = itemView.findViewById(R.id.tv_lastName)
            layout_container = itemView.findViewById(R.id.layout_userContainer)
            imageAvatar = itemView.findViewById(R.id.imageAvatar)
            imageOnline = itemView.findViewById(R.id.image_online)
            //itemView.setOnClickListener(this)
        }

        fun bind(list: VKUser) {
            this.list = list
            first_name.text = list.firstName
            last_name.text = list.lastName
            Picasso.get().load(list.photo).into(imageAvatar)
            if (!list.isOnline) {
                imageOnline.visibility = View.INVISIBLE
            }
        }

        override fun onClick(v: View?) {
            Toast.makeText(itemView.context, "${first_name.text} ${last_name.text}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        count++
        Log.i("self-rv", "onCreateViewHolder [count ${count}]")
        val view: View =
            LayoutInflater.from(parent?.context).inflate(R.layout.friends_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i("self-rv", "getItemCount [${userList.size}]")
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("self-rv", "onBindViewHolder[${position}]")
        holder.bind(userList[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            notifyItemMoved(position, position+1)
        })
        holder.itemView.setOnClickListener{

        }
        /*val value = userList.get(position)
        holder.first_name?.text = value.firstName
        holder.last_name?.text = value.lastName
        Picasso.get().load(value.photo).into(holder.imageAvatar)
        if(value.isOnline){
            holder.imageOnline!!.setImageResource(R.drawable.icon_online)
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            val click = it.resources.getDrawable(R.drawable.onclick, it.context.theme)
            val notClick = it.resources.getDrawable(R.drawable.notclick, it.context.theme)
            if(it.equals(currentClick)){
                it.background = notClick
                currentClick = null
                Log.i("self","click")
            }
            else{
                it.background = click
                currentClick?.background = notClick
                currentClick = it
            }
        })
    }*/
    }
}