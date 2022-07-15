package com.example.prueba_ceiba.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_ceiba.R
import com.example.prueba_ceiba.domain.entity.Post

class UserDetailAdapter(
    private val data: MutableList<Post>
): RecyclerView.Adapter<UserDetailAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_post, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val post = data[position]
        holder.tvTitle.text = post.title
        holder.tvBody.text = post.body
    }

    override fun getItemCount(): Int = data.size

    fun updateItems(items: MutableList<Post>){
        this.data.clear()
        this.data.addAll(items)
        this.notifyDataSetChanged()
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvBody: TextView = view.findViewById(R.id.tv_body)
    }
}