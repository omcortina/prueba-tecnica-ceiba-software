package com.example.prueba_ceiba.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_ceiba.R
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.view.UserView
import kotlinx.android.synthetic.main.item_list_uer.view.*

class UserAdapter(
    private val data: MutableList<User>,
    private val userView: UserView,
    val context: Context
): RecyclerView.Adapter<UserAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_uer, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = data[position]
        holder.tvName.text = user.name
        holder.tvPhone.text = user.phone
        holder.tvEmail.text = user.email
        holder.tvShowPost.setOnClickListener {
            userView.goToPosts(
                user.id, user.name, user.phone, user.email
            )
        }
    }

    override fun getItemCount(): Int = data.size

    fun updateItems(items: MutableList<User>){
        this.data.clear()
        this.data.addAll(items)
        this.notifyDataSetChanged()
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvName: TextView = view.tv_name
        val tvPhone: TextView = view.tv_phone
        val tvEmail: TextView = view.tv_email
        val tvShowPost: TextView = view.tv_show_posts
    }
}