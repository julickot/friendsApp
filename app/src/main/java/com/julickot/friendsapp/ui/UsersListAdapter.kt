package com.julickot.friendsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.julickot.friendsapp.data.User
import com.julickot.friendsapp.databinding.ListItemViewBinding
import com.julickot.friendsapp.ui.UsersListAdapter.ViewHolder


class UsersListAdapter(val itemHandler: UsersListItemHandler): ListAdapter<User, ViewHolder>(
    UserDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemHandler)
    }

    class ViewHolder private constructor(val binding: ListItemViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: User, itemHandler: UsersListItemHandler) {
            with(binding){
                person = item
                handler = itemHandler
                executePendingBindings()
            }
        }

        companion object{
            fun from(parent:ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemViewBinding.inflate(inflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class UsersListItemHandler(val clickListener:(user: User)->Unit){

    fun onClick(user: User) = clickListener(user)
}

class UserDiffCallback: DiffUtil.ItemCallback<User>(){

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

