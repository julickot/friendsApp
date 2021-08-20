package com.julickot.friendsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.julickot.friendsapp.R
import com.julickot.friendsapp.data.User
import com.julickot.friendsapp.databinding.FragmentNestedListBinding

class NestedListFragment: Fragment() {

    private lateinit var adapter: UsersListAdapter

    var onFriendClicked: ((user: User) -> Unit)? = null

    fun setData(data: List<User>) {
        adapter.submitList(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentNestedListBinding>(
            inflater,
            R.layout.fragment_nested_list,
            container,
            false
        )

        adapter = UsersListAdapter(UsersListItemHandler { item ->
            onFriendClicked?.invoke(item)
        })

        binding.usersList.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}


