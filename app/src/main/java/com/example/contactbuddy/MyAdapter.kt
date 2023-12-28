package com.example.contactbuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var userList:ArrayList<DataList>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var mListner: onitemClickListener
    interface onitemClickListener{
        fun onItemClick(position: Int)
    }
    fun onItemClickListener(Listener:onitemClickListener){
        mListner=Listener
    }

    class MyViewHolder(itemView: View,listener: onitemClickListener):RecyclerView.ViewHolder(itemView){
        val tname:TextView=itemView.findViewById(R.id.textView)
        val tnumber:TextView=itemView.findViewById(R.id.textView2)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
    fun updateList(newList: List<DataList>) {
        userList = ArrayList(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView,mListner)
    }


    override fun getItemCount(): Int {
       return  userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem=userList[position]
        holder.tname.text=currentItem.name
        holder.tnumber.text=currentItem.contact

    }
}