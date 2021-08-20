package com.julickot.friendsapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julickot.friendsapp.data.Repository
import com.julickot.friendsapp.data.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int, private val repository: Repository) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>> = _users

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _actionFlow = MutableSharedFlow<DetailAction>()
    val actionsFlow: SharedFlow<DetailAction> = _actionFlow

    init {
        viewModelScope.launch {
            repository.getUsers().collect { it ->
                val user = it.single { it.id == id }
                val friends = user.friends.map { it.id }
                val users = it.filter { friends.contains(it.id) }
                _users.value = users
                _user.value = user
            }
        }
    }

    fun onItemClicked(user: User) {
        if (user.isActive) {
            viewModelScope.launch {
                _actionFlow.emit(DetailAction.NavigateToDetail(user.id))
            }
        }
    }

    fun onPhoneClick(phone: String) {
        viewModelScope.launch {
            _actionFlow.emit(DetailAction.DoCallIntent(phone))
        }
    }

    fun onEmailClick(email: String) {
        viewModelScope.launch {
            _actionFlow.emit(DetailAction.DoSendEmailIntent(email))
        }
    }

    fun onLocationClick(latitude:Double, longitude:Double, userName: String) {
        viewModelScope.launch {
            _actionFlow.emit(DetailAction.DoLocationIntent(latitude, longitude, userName))
        }
    }
}