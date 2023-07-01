package com.example.ulinkchatbot.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ulinkchatbot.Models.MessageModel
import com.example.ulinkchatbot.R

class MessageAdapter(val list: ArrayList<MessageModel>): Adapter<MessageAdapter.MessageViewHolder>() {
    inner class MessageViewHolder(view:View):ViewHolder(view){
        val msgTxt = view.findViewById<TextView>(R.id.show_message)
        val imageContainer = view.findViewById<LinearLayout>(R.id.imageCard)
        val image = view.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var from = LayoutInflater.from(parent.context)
        val view  = if (viewType==0){
            from.inflate(R.layout.chatrightitem, parent,false)
        } else {
            from.inflate(R.layout.chatleftitem, parent,false)
        }
        return MessageViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val message = list[position]
        return if (message.isUser) 0 else 1
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = list[position]
        if (!message.isUser) {
            holder.imageContainer.visibility = View.GONE
        } else {
            holder.msgTxt.text = message.message
        }
    }
}