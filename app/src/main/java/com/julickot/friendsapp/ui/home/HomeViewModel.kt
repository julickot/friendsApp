package com.julickot.friendsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julickot.friendsapp.data.Repository
import com.julickot.friendsapp.data.Settings
import com.julickot.friendsapp.data.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(private val repository: Repository, settings: Settings): ViewModel() {

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>> = _users

    private val _actionFlow = MutableSharedFlow<HomeAction>()
    val actionsFlow: SharedFlow<HomeAction> = _actionFlow

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {

        if (settings.isFirstStart()) {
            refreshData()
            settings.setStarted()
        }

        viewModelScope.launch {
            repository.getUsers().collect {
                _users.value = it
            }
        }
    }

    fun onItemClicked(user: User) {
        if (user.isActive) {
            viewModelScope.launch {
                _actionFlow.emit(HomeAction.NavigateToDetail(user.id))
            }
        }
    }

    fun onRefreshPulled() {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                repository.refreshData()
            } catch (networkError: IOException) {
                networkError.message?.let {
                    _actionFlow.emit(HomeAction.MakeToast(it))
                }
            }finally {
                _isLoading.value = false
            }
        }
    }
}