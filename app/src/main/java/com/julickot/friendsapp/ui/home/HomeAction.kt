package com.julickot.friendsapp.ui.home

sealed class HomeAction{
    class NavigateToDetail(val id:Int): HomeAction()
    class MakeToast(val text:String) : HomeAction()
}
