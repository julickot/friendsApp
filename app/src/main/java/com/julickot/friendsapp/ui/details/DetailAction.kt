package com.julickot.friendsapp.ui.details

sealed class DetailAction{
    class NavigateToDetail(val id:Int): DetailAction()
    class DoCallIntent(val phone: String) : DetailAction()
    class DoSendEmailIntent(val email: String) : DetailAction()
    class DoLocationIntent(val latitude: Double, val longitude: Double, val label:String) : DetailAction()
}