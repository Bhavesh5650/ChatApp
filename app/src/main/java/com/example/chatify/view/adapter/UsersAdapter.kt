package com.example.chatify.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatify.R
import com.example.chatify.data.model.UserModel
import com.example.chatify.databinding.AllUsersSampleLayoutBinding
import com.example.chatify.view.activity.ChatingActivity

class UsersAdapter(private var userList:MutableList<UserModel>) : Adapter<UsersAdapter.UsersViewHolder>() {

    class UsersViewHolder(itemView: View) : ViewHolder(itemView)
    {
        val binding = AllUsersSampleLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_users_sample_layout,parent,false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.binding.setUserNameUser.text = userList[position].name
        holder.binding.setFirstLetterUser.text = userList[position].name.first().toString().uppercase()
        holder.binding.userBackground.setOnClickListener {
            val intent = Intent(holder.itemView.context,ChatingActivity::class.java)
            intent.putExtra("uid",userList[position].uid)
            intent.putExtra("name",userList[position].name)
            intent.putExtra("mobile",userList[position].mobile)
            holder.itemView.context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(list: MutableList<UserModel>)
    {
        userList = list
        notifyDataSetChanged()
    }
}